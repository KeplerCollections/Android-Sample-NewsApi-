package com.kepler.androidsamplemynewsapi;

import android.app.Application;

import com.kepler.androidsamplemynewsapi.injection.component.ApiComponent;
import com.kepler.androidsamplemynewsapi.injection.component.DaggerApiComponent;
import com.kepler.androidsamplemynewsapi.injection.component.DaggerRemoteDataSourceComponent;
import com.kepler.androidsamplemynewsapi.injection.component.DaggerRepoComponent;
import com.kepler.androidsamplemynewsapi.injection.component.RemoteDataSourceComponent;
import com.kepler.androidsamplemynewsapi.injection.component.RepoComponent;

public class MyApplication extends Application {

    private ApiComponent mApiComponent;
    private RemoteDataSourceComponent remoteDataSourceComponent;
    private RepoComponent repoComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApiComponent = DaggerApiComponent.create();
        remoteDataSourceComponent = DaggerRemoteDataSourceComponent.create();
        repoComponent = DaggerRepoComponent.create();

    }


    public ApiComponent getmApiComponent() {
        return mApiComponent;
    }

    public RemoteDataSourceComponent getRemoteDataSourceComponent() {
        return remoteDataSourceComponent;
    }

    public RepoComponent getRepoComponent() {
        return repoComponent;
    }
}