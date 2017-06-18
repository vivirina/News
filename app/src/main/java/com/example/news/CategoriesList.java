package com.example.news;

import android.util.Log;

import com.example.news.api.Request;
import com.example.news.api.Response;
import com.example.news.api.Server;
import com.example.news.api.ServerMethod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public enum CategoriesList {
    INSTANCE;

    public  static  CategoriesList getInstance(){
        return INSTANCE;
    }

    private List<Category> categories = new ArrayList<>();

    private CategoriesList(){

    }

    private boolean updateInProgress = false;

    public interface OnUpdateListener {
        public void updateStarted();
        public void updateFinished(boolean success);
    }

    private List<OnUpdateListener> listeners = new ArrayList<>();

    public void addListener(OnUpdateListener listener) {
        listeners.add(listener);
    }

    public void removeListener(OnUpdateListener listener) {
        listeners.remove(listener);
    }

    public void update() {
        if (updateInProgress) {
            return;
        }
        updateInProgress = true;

        for (OnUpdateListener listener : listeners) {
            listener.updateStarted();
        }

        Request request = new Request(ServerMethod.CATEGORIES, "GET");
        Server.performRequestAsync(request, new Server.OnRequestListener() {
            @Override
            public void onRequestComplete(Response response) {
                boolean success = true;

                if (response.isSuccessful()) {
                    try {
                        List<Category> categories = new ArrayList<Category>();
                        JSONObject json = response.getJson();
                        JSONArray array = json.getJSONArray("list");
                        for (int i = 0; i < array.length(); ++i) {
                            JSONObject obj = array.getJSONObject(i);
                            Category category = new Category(obj);
                            categories.add(category);
                        }
                        CategoriesList.this.categories = categories;
                    } catch (JSONException e) {
                        Log.e("Categories", "Failed to update categories", e);
                        success = false;
                    }
                } else {
                    success = false;
                }

                updateInProgress = false;
                for (OnUpdateListener listener : listeners) {
                    listener.updateFinished(success);
                }
            }
        });
    }

    public List<Category> getCategories(){
        return categories;
    }

    public Category getCategory(int Id){
        for(Category category : categories){
            if (category.getId() == Id){
                return category;
            }
        }
        return null;
    }

}
