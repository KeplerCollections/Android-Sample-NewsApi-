package com.kepler.androidsamplemynewsapi.api;

import com.kepler.androidsamplemynewsapi.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {


    public ApiClient() {
    }

    public static Retrofit getClient() {
        return new retrofit2.Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}