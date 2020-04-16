package com.kepler.androidsamplemynewsapi.injection.module;

import android.app.Application;

import com.kepler.androidsamplemynewsapi.api.remote.RemoteDataSource;
import com.kepler.androidsamplemynewsapi.api.repo.Repository;

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