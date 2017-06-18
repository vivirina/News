package com.example.news.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ирина on 07.06.2017.
 */

public class Request {
    private ServerMethod method;
    private String httpMethod;
    private Map<String, String> params;
    private List<String> urlParams;

    public Request(ServerMethod method, String httpMethod) {
        this.method = method;
        this.httpMethod = httpMethod;
        this.params = new HashMap<>();
        this.urlParams = new ArrayList<>();
    }

    public void addParameter(String key, String value) {
        this.params.put(key, value);
    }

    public ServerMethod getMethod() {
        return method;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void addUrlParameter(String parameter){
        this.urlParams.add(parameter);
    }

    public List<String> getUrlParams(){
        return urlParams;
    }

    @Override
    public String toString() {
        return "Request{" +
                "method=" + method +
                ", httpMethod='" + httpMethod + '\'' +
                ", params=" + params +
                '}';
    }
}
