package com.kepler.androidsamplemynewsapi.api.repo;

import androidx.annotation.NonNull;

import com.kepler.androidsamplemynewsapi.api.IDataSource;
import com.kepler.androidsamplemynewsapi.pojo.Article;
import com.kepler.androidsamplemynewsapi.pojo.Source;

import java.util.List;

public interface IRepositoryData {

    void getArticles(String source,
                     IDataSource.LoadDataCallback<Article> callback,
                     boolean isNetworkAvailable);

    void getSources(@NonNull IDataSource.LoadDataCallback<Source> callback,
                    boolean isNetworkAvailable);


}