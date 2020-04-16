package com.kepler.androidsamplemynewsapi;


import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.kepler.androidsamplemynewsapi.boilers.BaseActivity;

import butterknife.BindView;

import static com.kepler.androidsamplemynewsapi.support.Constants.PARAM_URL;

public class BrowserActivity extends BaseActivity {

    @BindView(R.id.webview)
    WebView webView;
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
        webView.setWebViewClient(new WebViewClient());
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_web_view;
    }

}
