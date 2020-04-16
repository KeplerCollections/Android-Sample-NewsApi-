package com.kepler.androidsamplemynewsapi;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.kepler.androidsamplemynewsapi.boilers.BaseActivity;

import butterknife.BindView;

import static com.kepler.androidsamplemynewsapi.support.Constants.PARAM_URL;

public class BrowserActivity extends BaseActivity {

    @BindView(R.id.webview)
    WebView webView;
    @BindView(R.id.progrssBar)
    ProgressBar progrssBar;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableBackButton();
        String url = getIntent().getStringExtra(PARAM_URL);
        setActionBarTitle(url);
        webView.loadUrl(url);

        // this will enable the javascipt.
        webView.getSettings().setJavaScriptEnabled(true);

        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressing(true);
                handler.postDelayed(() -> {
                    progressing(false);
                }, 5000);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
    }

    private void progressing(boolean progressing) {
        if (progrssBar == null)
            return;
        progrssBar.setIndeterminate(progressing);
        if (progressing) {
            progrssBar.setVisibility(View.VISIBLE);
        } else {
            progrssBar.setVisibility(View.GONE);
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_web_view;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        webView.destroy();
    }
}
