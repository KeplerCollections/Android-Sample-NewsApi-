package com.kepler.androidsamplemynewsapi.api;


import com.kepler.androidsamplemynewsapi.pojo.ArticleResponse;
import com.kepler.androidsamplemynewsapi.pojo.SourceResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("everything")
    Call<ArticleResponse> getArticle(
            @Query("apiKey") String apiKey,
            @Query("sources") String sources);

    //top, latest, popular
    //Possible category options: business, entertainment, gaming, general, music, science-and-nature, sport, technology.
    //Possible language options: en, de, fr.
    //Possible country options: au, de, gb, in, it, us.
    @GET("sources")
    Call<SourceResponse> getSource(@Query("apiKey") String apiKey,
                                   @Query("language") String language);

}