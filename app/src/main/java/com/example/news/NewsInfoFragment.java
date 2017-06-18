package com.example.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.news.api.Request;
import com.example.news.api.Response;
import com.example.news.api.Server;
import com.example.news.api.ServerMethod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewsInfoFragment extends Fragment {

    public static final String INTENT_EXTRA_CATEGORY_ID = "category_id";
    public static final String INTENT_EXTRA_NEWS_NUMBER = "number";

    private Category category;
    private News news;

    private NewsCell cellView;
    private TextView shortDescriptionNewsView;
    private WebView textNewsView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int categoryId = getArguments().getInt(INTENT_EXTRA_CATEGORY_ID);
        int newsId = getArguments().getInt(INTENT_EXTRA_NEWS_NUMBER);
        category = CategoriesList.getInstance().getCategory(categoryId);
        news = NewsList.getInstance(category).getNews(newsId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(com.example.news.R.layout.news_info_fragment, container, false);

        cellView = (NewsCell) view.findViewById(com.example.news.R.id.cell);
        shortDescriptionNewsView = (TextView) view.findViewById(R.id.shortdescription);
        textNewsView = (WebView) view.findViewById(com.example.news.R.id.fulldescription);

        cellView.setNews(news);
        shortDescriptionNewsView.setText(news.getShortdescription());
        textNewsView.loadData(news.getFulldescription(),"text/html",null);

        return view;
    }
}
