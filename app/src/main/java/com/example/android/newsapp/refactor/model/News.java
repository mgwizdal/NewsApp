package com.example.android.newsapp.refactor.model;

import com.google.gson.annotations.SerializedName;

public class News {
    @SerializedName("response")
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
