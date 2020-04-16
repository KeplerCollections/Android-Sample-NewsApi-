package com.kepler.androidsamplemynewsapi.boilers;

public interface BaseFragmentCommunicator {

    void toast(String msg);

    void loadArticle(String source);

    void openUrl(String url);

}