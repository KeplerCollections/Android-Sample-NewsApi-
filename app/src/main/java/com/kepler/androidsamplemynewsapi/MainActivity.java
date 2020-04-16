package com.kepler.androidsamplemynewsapi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.kepler.androidsamplemynewsapi.boilers.BaseFragmentCommunicator;
import com.kepler.androidsamplemynewsapi.boilers.MVPActivity;
import com.kepler.androidsamplemynewsapi.pojo.Article;
import com.kepler.androidsamplemynewsapi.pojo.Source;
import com.kepler.androidsamplemynewsapi.presenters.AppLogic;
import com.kepler.androidsamplemynewsapi.presenters.MainPresnter;
import com.kepler.androidsamplemynewsapi.support.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends MVPActivity<AppLogic.MainLogic> implements AppLogic.MainView, BaseFragmentCommunicator {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.messageView)
    TextView messageView;
    @BindView(R.id.progrssBar)
    ProgressBar progrssBar;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    private TabAdapter adapter;

    @Override
    protected AppLogic.MainLogic createPresenter() {
        return new MainPresnter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.loadSource();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected int getContainerId() {
        return 0;
    }

    @Override
    public void startProgressing() {
        progrssBar.setIndeterminate(true);
        progrssBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopProgressing() {
        progrssBar.setVisibility(View.GONE);
        progrssBar.setIndeterminate(false);

    }

    @Override
    public void showNoSourcesData() {
        mViewPager.setVisibility(View.GONE);
        messageView.setText(R.string.data_unavailable);
        messageView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoadingSourcesError() {
        mViewPager.setVisibility(View.GONE);
        messageView.setText(R.string.data_error);
        messageView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showSources(List<Source> sources) {
        if (adapter == null) {
            adapter = new TabAdapter(getSupportFragmentManager(), sources);
            mViewPager.setAdapter(adapter);
            mTabLayout.setupWithViewPager(mViewPager);
        }
    }

    @Override
    public boolean isNetworkAvailable() {
        return isNetworkConnected();
    }

    @Override
    public void showArticles(List<Article> list, String source) {
        Intent intentMultiPurpose = new Intent();
        intentMultiPurpose.setAction(Constants.ACTION.ACTION_ARTICLE_ARRIVED);
        intentMultiPurpose.putParcelableArrayListExtra(Constants.PARAM_DATA, (ArrayList<? extends Parcelable>) list);
        intentMultiPurpose.putExtra(Constants.PARAM_SOURCE, source);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intentMultiPurpose);

    }


    @Override
    public void toast(String msg) {

    }

    @Override
    public void loadArticle(String source) {
        presenter.loadArticle(source);
    }

    @Override
    public void openUrl(String url) {
        openInAppBrowser(url);
    }
}
