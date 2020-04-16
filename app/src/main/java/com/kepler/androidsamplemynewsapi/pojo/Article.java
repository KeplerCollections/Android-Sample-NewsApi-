package com.kepler.androidsamplemynewsapi.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Article implements Parcelable {

    @Expose
    private String sourceId;
    @SerializedName("author")
    private String author;
    @SerializedName("urlToImage")
    private String imageUrl;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String decr;
    @SerializedName("url")
    private String url;
    @SerializedName("publishedAt")
    private String publishedAt;

    public Article() {
    }

    public Article(String author, String imageUrl, String title, String decr, String url, String publishedAt) {
        this.author = author;
        this.imageUrl = imageUrl;
        this.title = title;
        this.decr = decr;
        this.url = url;
        this.publishedAt = publishedAt;
    }

    protected Article(Parcel in) {
        sourceId = in.readString();
        author = in.readString();
        imageUrl = in.readString();
        title = in.readString();
        decr = in.readString();
        url = in.readString();
        publishedAt = in.readString();
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDecr() {
        return decr;
    }

    public void setDecr(String decr) {
        this.decr = decr;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sourceId);
        dest.writeString(author);
        dest.writeString(imageUrl);
        dest.writeString(title);
        dest.writeString(decr);
        dest.writeString(url);
        dest.writeString(publishedAt);
    }
    /*{
            "author": "Rachel Kaser",
            "title": "Kim Kardashian’s new bot wants to solve puzzles with you",
            "description": "If you’ve ever wanted to  …",
            "url": "https://thenextweb.com/apps/2017/03/23/kim-kardashians-new-bot-wants-solve-puzzles/",
            "urlToImage": "https://cdn0.tnwcdn.com/wp-content/blogs.dir/1/files/2017/03/KKbot-Header.jpg",
            "publishedAt": "2017-03-23T20:19:07Z"
    },*/
}