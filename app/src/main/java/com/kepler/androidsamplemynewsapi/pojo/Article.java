package com.kepler.androidsamplemynewsapi.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Article implements Parcelable {

    @SerializedName("urlToImage")
    private final String imageUrl;
    @SerializedName("title")
    private final String title;
    @SerializedName("url")
    private final String url;
    @SerializedName("publishedAt")
    private final String publishedAt;

    private Article(Parcel in) {
        imageUrl = in.readString();
        title = in.readString();
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


    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }


    public String getUrl() {
        return url;
    }


    public String getPublishedAt() {
        return publishedAt;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageUrl);
        dest.writeString(title);
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