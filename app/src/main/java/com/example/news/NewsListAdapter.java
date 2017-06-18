package com.example.news;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class NewsListAdapter  extends RecyclerView.Adapter<NewsListAdapter.NewsListCell>{

    private List<News> newsList;
    private OnNewsSelectedListener listener;
    private News selectedNews;

    public NewsListAdapter(List<News> newsList){
        this.newsList = new ArrayList<>(newsList);
    }

    public List<News> getNewsList(){
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
        notifyDataSetChanged();     // добавлен
    }

    public OnNewsSelectedListener getListener() {
        return listener;
    }

    public void setListener(OnNewsSelectedListener listener) {
        this.listener = listener;
    }

    @Override
    public NewsListCell onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(com.example.news.R.layout.news_list_cell, parent, false);
        return new NewsListCell(itemView, listener);
    }

    @Override
    public int getItemCount(){
        return newsList.size();
    }

    @Override
    public void onBindViewHolder(NewsListCell holder, int position){
        holder.setNews(newsList.get(position));
    }

    class NewsListCell extends RecyclerView.ViewHolder{

        private News news;

        private NewsCell newsCellView;

        public NewsListCell(View itemView, final OnNewsSelectedListener listener){
            super(itemView);

            newsCellView = (NewsCell) itemView.findViewById(com.example.news.R.id.cell);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    if(listener != null){
                        listener.onNewsSelected(news);
                    }
                }
            });

        }

        public News getNews() {
            return news;
        }

        public void setNews(News news) {
            this.news = news;

            newsCellView.setNews(news);

            if (selectedNews != null && this.news.getNumber() == selectedNews.getNumber()){
                itemView.setSelected(true);
            }
            else {
                itemView.setSelected(false);
            }
        }
    }

    public News getSelectedNews() {
        return selectedNews;
    }

    public void setSelectedNews(News selectedNews) {
        this.selectedNews = selectedNews;
        notifyDataSetChanged();
    }
}

