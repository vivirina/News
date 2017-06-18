package com.example.news;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Ирина on 01.06.2017.
 */

public class NewsesFragmentPagerAdapter extends FragmentStatePagerAdapter{

    private Category category;
    private List<News> newsList;

    public NewsesFragmentPagerAdapter(FragmentManager fm, Category category, List<News> newsList) {
        super(fm);
        this.category = category;
        this.newsList = newsList;
    }

    // добавлено (начало)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
        notifyDataSetChanged();
    }
    // добавлено (конец)

    @Override
    public Fragment getItem(int position) {
        News news = newsList.get(position);

        NewsInfoFragment infoFragment = new NewsInfoFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(NewsInfoFragment.INTENT_EXTRA_CATEGORY_ID, category.getId());
        arguments.putInt(NewsInfoFragment.INTENT_EXTRA_NEWS_NUMBER, news.getNumber());
        infoFragment.setArguments(arguments);
        return infoFragment;
    }

    @Override
    public int getCount() {
        return newsList.size();
    }
}

