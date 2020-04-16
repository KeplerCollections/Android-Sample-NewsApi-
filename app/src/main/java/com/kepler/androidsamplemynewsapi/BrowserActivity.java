package com.kepler.androidsamplemynewsapi;


import android.graphics.Bitmap;
import android.os.Bundle;
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
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableBackButton();
        url = getIntent(    ).getStringExtra(PARAM_URL);
        setActionBarTitle(url);
        webView.loadUrl(url);

        // this will enable the javascipt.
        webView.getSettings().setJavaScriptEnabled(true);

        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        webView.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressing(true);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressing(false);
            }
        });
    }

    private void progressing(boolean progressing)
    {
        if(progrssBar==null)
            return;
        if(progressing){
            progrssBar.setIndeterminate(true);
            progrssBar.setVisibility(View.VISIBLE);
        }else {
            progrssBar.setVisibility(View.GONE);
            progrssBar.setIndeterminate(false);
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
