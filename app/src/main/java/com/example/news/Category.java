package com.example.news;


import org.json.JSONException;
import org.json.JSONObject;

public class Category {

    private int id;
    private String shortName;

    public Category(int id, String shortName) {
        this.id = id;
        this.shortName = shortName;
    }

    public Category(JSONObject json) throws JSONException {
        this.id = json.getInt("id");
        this.shortName = json.getString("name");
    }

    public int getId() {
        return id;
    }

    public String getShortName() {
        return shortName;
    }

}
