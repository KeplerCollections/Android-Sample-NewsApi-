package com.kepler.androidsamplemynewsapi.injection.module;

import com.kepler.androidsamplemynewsapi.api.remote.RemoteDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RemoteDataSourceModule {

    @Provides
    @Singleton
    RemoteDataSource provideRepo() {
        return new RemoteDataSource();
    }
}