package com.example.news;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

public class NewsCell extends ConstraintLayout{

    private News news;

    //private TextView nameCategoryView;
    private  TextView nameNewsView;

    public NewsCell(Context context, AttributeSet attrs){
        super(context, attrs);
        LayoutInflater.from(context).inflate(com.example.news.R.layout.news_cell,this);
    }

    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();

        if (isInEditMode()){
            return;
        }

        //nameCategoryView = (TextView) findViewById(com.example.news.R.id.shortName);
        nameNewsView = (TextView) findViewById(R.id.title);

        nameNewsView.setText(Html.fromHtml("<b>text</b>"));
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;

        //nameCategoryView.setText(news.getCategory().getShortName());
        nameNewsView.setText(news.getTitle());
    }
}
