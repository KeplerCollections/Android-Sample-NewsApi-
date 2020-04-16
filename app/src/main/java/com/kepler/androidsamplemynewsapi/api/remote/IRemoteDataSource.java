package com.kepler.androidsamplemynewsapi.api.remote;


import androidx.annotation.NonNull;

import com.kepler.androidsamplemynewsapi.api.IDataSource;
import com.kepler.androidsamplemynewsapi.pojo.Article;
import com.kepler.androidsamplemynewsapi.pojo.Source;

public interface IRemoteDataSource extends IDataSource {

    void getSources(@NonNull LoadDataCallback<Source> callback);

    void getArticles(String source, @NonNull LoadDataCallback<Article> callback);
}