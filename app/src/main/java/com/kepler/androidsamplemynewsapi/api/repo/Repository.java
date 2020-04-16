package com.kepler.androidsamplemynewsapi.api.repo;

import androidx.annotation.NonNull;

import com.kepler.androidsamplemynewsapi.api.IDataSource;
import com.kepler.androidsamplemynewsapi.api.remote.IRemoteDataSource;
import com.kepler.androidsamplemynewsapi.api.remote.RemoteDataSource;
import com.kepler.androidsamplemynewsapi.injection.component.DaggerRemoteDataSourceComponent;
import com.kepler.androidsamplemynewsapi.pojo.Article;
import com.kepler.androidsamplemynewsapi.pojo.Source;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static androidx.core.util.Preconditions.checkNotNull;

public class Repository implements IRepositoryData {
    @Inject
    RemoteDataSource remoteDataSource;


    public Repository() {
        DaggerRemoteDataSourceComponent.create().inject(this);

    }

    @Override
    public void getArticles(String source, IDataSource.LoadDataCallback<Article> callback, boolean isNetworkAvailable) {
        checkNotNull(callback);
        if (isNetworkAvailable) {
            getArticlesFromRemoteDataSource(source, callback);
        } else {
            callback.onDataNotAvailable();
        }
    }

    private void getArticlesFromRemoteDataSource(final String sourceId,
                                                 final IDataSource.LoadDataCallback<Article> callback) {

        remoteDataSource.getArticles(sourceId, new IDataSource.LoadDataCallback<Article>() {
            @Override
            public void onDataLoaded(List<Article> articles) {
                callback.onDataLoaded(new ArrayList<>(articles));
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }


    @Override
    public void getSources(@NonNull IDataSource.LoadDataCallback<Source> callback, boolean isNetworkAvailable) {
        checkNotNull(callback);
        if (isNetworkAvailable) {
            getSourcesFromRemoteDataSource(callback);
        } else {
            callback.onDataNotAvailable();
        }
    }

    private void getSourcesFromRemoteDataSource(
            @NonNull final IRemoteDataSource.LoadDataCallback<Source> callback) {

        remoteDataSource.getSources(new IRemoteDataSource.LoadDataCallback<Source>() {
            @Override
            public void onDataLoaded(List<Source> sources) {
                callback.onDataLoaded(new ArrayList<>(sources));
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

}
