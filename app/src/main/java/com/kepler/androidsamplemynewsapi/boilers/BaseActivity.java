package com.kepler.androidsamplemynewsapi.boilers;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kepler.androidsamplemynewsapi.BrowserActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.kepler.androidsamplemynewsapi.support.Constants.PARAM_URL;

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder unbinder;
    private ConnectivityManager cm;

    //in your Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        unbinder = ButterKnife.bind(this);
        init();
    }

    private void init() {
        cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    protected abstract int getContentView();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void enableBackButton() {
        if (getSupportActionBar() == null)
            return;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    protected void disableBackButton() {
        if (getSupportActionBar() == null)
            return;
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    protected void startActivity(Class baseActivity) {
        startActivity(new Intent(this, baseActivity));

    }

    protected void setActionBarTitle(int title) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(title);
    }

    protected void setActionBarTitle(String title) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(title);
    }

    protected void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected boolean isNetworkConnected() {
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    protected void openInAppBrowser(String url) {
        Intent intent = new Intent(this, BrowserActivity.class);
        intent.putExtra(PARAM_URL, url);
        startActivity(intent);
    }
}
