package com.example.news.api;

import java.util.List;
import java.util.Locale;

/**
 * Created by Ирина on 07.06.2017.
 */

public enum ServerMethod {
    CATEGORIES("/v1/news/categories"),
    NEWSLIST("/v1/news/categories/%s/news"),
    NEWSDETAILS("/v1/news/details");

    private String path;

    private ServerMethod(String path) {
        this.path = path;
    }

    public String getPath(List<String> parameters) {
        if(parameters == null || parameters.isEmpty()){
            return path;
        }
        else {
            return String.format(Locale.US, path, parameters.toArray(new String[parameters.size()]));
        }
    }
}
