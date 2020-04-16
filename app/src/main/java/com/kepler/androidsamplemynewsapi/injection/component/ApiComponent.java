package com.kepler.androidsamplemynewsapi.injection.component;

import com.kepler.androidsamplemynewsapi.api.remote.RemoteDataSource;
import com.kepler.androidsamplemynewsapi.injection.module.ApiModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class})
public interface ApiComponent {
    void inject(RemoteDataSource remoteDataSource);
}