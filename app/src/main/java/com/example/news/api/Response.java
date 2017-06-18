package com.example.news.api;

import org.json.JSONObject;

/**
 * Created by Ирина on 07.06.2017.
 */

public class Response {
    private int statusCode;
    private JSONObject json;

    public Response(int statusCode) {
        this.statusCode = statusCode;
    }

    public Response(int statusCode, JSONObject json) {
        this.statusCode = statusCode;
        this.json = json;
    }

    public boolean isSuccessful()  {
        return statusCode == 200;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public JSONObject getJson() {
        return json;
    }

    @Override
    public String toString() {
        return "Response{" +
                "statusCode=" + statusCode +
                ", json=" + json +
                '}';
    }
}
