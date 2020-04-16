package com.kepler.androidsamplemynewsapi.api.remote;

import android.util.Log;

import androidx.annotation.NonNull;

import com.kepler.androidsamplemynewsapi.BuildConfig;
import com.kepler.androidsamplemynewsapi.api.ApiService;
import com.kepler.androidsamplemynewsapi.injection.component.DaggerApiComponent;
import com.kepler.androidsamplemynewsapi.pojo.Article;
import com.kepler.androidsamplemynewsapi.pojo.ArticleResponse;
import com.kepler.androidsamplemynewsapi.pojo.Source;
import com.kepler.androidsamplemynewsapi.pojo.SourceResponse;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class RemoteDataSource implements IRemoteDataSource {

    private final ApiService service;
    @Inject
    Retrofit retrofit;

    public RemoteDataSource() {
        DaggerApiComponent.create().inject(this);
        service = retrofit.create(ApiService.class);
    }

    private static final String LOG_TAG = RemoteDataSource.class.getSimpleName();

    @Override
    public void getSources(@NonNull final LoadDataCallback<Source> callback) {
        Call<SourceResponse> articleResponseCall = service.getSource(BuildConfig.API_KEY, "en");
        articleResponseCall.enqueue(new Callback<SourceResponse>() {
            @Override
            public void onResponse(Call<SourceResponse> call, Response<SourceResponse> response) {
                if (response.body() == null || !"ok".equals(response.body().getStatus())) {
                    callback.onDataNotAvailable();
                    Log.e(LOG_TAG, "Oops, something went wrong!");
                } else {
                    if (!response.body().getSources().isEmpty()) {
                        callback.onDataLoaded(response.body().getSources());
                    } else {
                        callback.onDataNotAvailable();
                        Log.e(LOG_TAG, "Oops, something went wrong!");
                    }
                }
            }

            @Override
            public void onFailure(Call<SourceResponse> call, Throwable t) {
                Log.e(LOG_TAG, "Error:" + t.getMessage());
                callback.onDataNotAvailable();
            }
        });

    }

    public void getArticles(String source, @NonNull final LoadDataCallback<Article> callback) {
        Call<ArticleResponse> articleResponseCall = service.getArticle(BuildConfig.API_KEY, source);
        articleResponseCall.enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                if (response.body() == null || !"ok".equals(response.body().getStatus())) {
                    Log.e(LOG_TAG, "Oops, something went wrong!");
                    callback.onDataNotAvailable();
                } else {
                    if (!response.body().getArticles().isEmpty()) {
                        callback.onDataLoaded(response.body().getArticles());
                    } else {
                        callback.onDataNotAvailable();
                        Log.e(LOG_TAG, "Oops, something went wrong!");
                    }
                }
            }

            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {
                Log.e(LOG_TAG, "Error:" + t.getMessage());
                callback.onDataNotAvailable();

            }
        });

    }
}