package com.kepler.androidsamplemynewsapi.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Source {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;


    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    /*sources": [
            -
    {
        "id": "abc-news-au",
            "name": "ABC News (AU)",
            "description": "Australia's most truste ... the latest business, sport, weather and more.",
            "url": "http://www.abc.net.au/news",
            "category": "general",
            "language": "en",
            "country": "au",
            -
                    "urlsToLogos": {
        "small": "http://i.newsapi.org/abc-news-au-s.png",
                "medium": "http://i.newsapi.org/abc-news-au-m.png",
                "large": "http://i.newsapi.org/abc-news-au-l.png"
    },
        -
                "sortBysAvailable": [
        "top"
]
    }
*/

}