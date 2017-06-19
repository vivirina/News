package com.example.news;

import android.util.Log;
import android.widget.Toast;

import com.example.news.api.Request;
import com.example.news.api.Response;
import com.example.news.api.Server;
import com.example.news.api.ServerMethod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class News{

    private int number;
    private String title;
    private String shortdescription;
    private String fulldescription;
    private Category category;


    public News(int number, String title, String description, String fulldescription, Category category) {
        this.number = number;
        this.title = title;
        this.shortdescription = description;
        this.fulldescription = fulldescription;
        this.category = category;
    }

    public void parseJson(JSONObject json)throws JSONException {
        this.number = json.getInt("id");
        this.title = json.getString("title");
        this.shortdescription = json.getString("shortDescription");
        if (json.has("fullDescription")) {
            this.fulldescription = json.getString("fullDescription");
        }
        this.category = category;
    }

    public News(JSONObject json)throws JSONException{
        parseJson(json);
    }

    public News(JSONObject json, Category category) throws JSONException{
        this.number = json.getInt("id");
        this.title = json.getString("title");
        this.shortdescription = json.getString("shortDescription");
        if (json.has("fullDescription")) {
            this.fulldescription = json.getString("fullDescription");
        }
        //this.category = category;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortdescription() {
        return shortdescription;
    }

    public void setShortdescription(String shortdescription) {
        this.shortdescription = shortdescription;
    }

    public String getFulldescription() {
        return fulldescription;
    }

    public void setFulldescription(String fulldescription) {
        this.fulldescription = fulldescription;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    //   добавлено (начало)

    private boolean updateInProgress = false;

    public interface OnUpdateListener{
        public void updateStarted();
        public void updateFinished(boolean success);
    }

    private List<NewsList.OnUpdateListener> listeners = new ArrayList<>();

    public void addListener(NewsList.OnUpdateListener listener){
        listeners.add(listener);
    }

    public void removeListener(NewsList.OnUpdateListener listener){
        listeners.remove(listener);
    }

    public void update(){
        if (updateInProgress){
            return;
        }
        updateInProgress = true;

        for(NewsList.OnUpdateListener listener : listeners){
            listener.updateStarted();
        }

        Request request = new Request(ServerMethod.NEWSDETAILS,"GET");
        request.addParameter("id",Integer.toString(number));
        Server.performRequestAsync(request, new Server.OnRequestListener(){

            @Override
            public void onRequestComplete(Response response) {
                boolean success = true;

                if(response.isSuccessful()){
                    try {
                        JSONObject json = response.getJson();
                        JSONObject obj = json.getJSONObject("news");
                        parseJson(json);
                    }
                    catch (JSONException e){
                        Log.e("NewsDetails","Failed to update newsdetails", e);
                        success = false;
                    }
                }
                else {
                    success = false;
                }
                updateInProgress = false;
                for (NewsList.OnUpdateListener listener : listeners){
                    listener.updateFinished(success);
                }
            }
        });
    }
    //   добавлено (конец)
}
