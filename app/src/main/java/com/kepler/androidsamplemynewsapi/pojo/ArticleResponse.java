package com.kepler.androidsamplemynewsapi.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Igor Havrylyuk on 27.03.2017.
 */
public class ArticleResponse {

    @SerializedName("status")
    private String status;
    @SerializedName("source")
    private String source;
    @SerializedName("articles")
    private List<Article> articles;


    public String getStatus() {
        return status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<Article> getArticles() {
        return articles;
    }

}