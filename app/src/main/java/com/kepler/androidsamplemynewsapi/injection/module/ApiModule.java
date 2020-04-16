package com.kepler.androidsamplemynewsapi.injection.module;

import android.app.Application;

import com.kepler.androidsamplemynewsapi.BuildConfig;
import com.kepler.androidsamplemynewsapi.api.repo.Repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    @Provides
    @Singleton
    Retrofit provideApiService() {
         return new retrofit2.Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}