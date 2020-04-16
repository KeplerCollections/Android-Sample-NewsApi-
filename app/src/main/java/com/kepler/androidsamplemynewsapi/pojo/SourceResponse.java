package com.kepler.androidsamplemynewsapi.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SourceResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("sources")
    private List<Source> sources;

    public SourceResponse() {
    }

    public String getStatus() {
        return status;
    }


    public List<Source> getSources() {
        if (sources.size() > 10)
            return sources.subList(0, 10);
        return sources;
    }

}