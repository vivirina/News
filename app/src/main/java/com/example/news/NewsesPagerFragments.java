package com.example.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Ирина on 01.06.2017.
 */

public class NewsesPagerFragments extends NewsSelectingFragment implements NewsList.OnUpdateListener{

    public static final String INTENT_EXTRA_CATEGORY_ID = "category_id";
    public static final String INTENT_EXTRA_NEWS_NUMBER = "number";

    private ViewPager viewPager;
    private NewsesFragmentPagerAdapter adapter;     // добавлено

    private Category category;
    private NewsList newsList;
    private News selectedNews;

    private boolean tablet;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int categoryId = getArguments().getInt(INTENT_EXTRA_CATEGORY_ID);
        category = CategoriesList.getInstance().getCategory(categoryId);
        newsList = NewsList.getInstance(category);

        if (savedInstanceState != null) {
            selectedNews = newsList.getNews(savedInstanceState.getInt(INTENT_EXTRA_NEWS_NUMBER, 0));
        } else if (getArguments() != null) {
            selectedNews = newsList.getNews(getArguments().getInt(INTENT_EXTRA_NEWS_NUMBER, 0));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(com.example.news.R.layout.news_pager_fragment, container, false);

        tablet = getResources().getBoolean(com.example.news.R.bool.tablet);

        viewPager = (ViewPager) view.findViewById(com.example.news.R.id.viewPager);

        final List<News> newsListCat = newsList.getNewsList();
        //viewPager.setAdapter(new NewsesFragmentPagerAdapter(getChildFragmentManager(), category, newsListCat));
        adapter = new NewsesFragmentPagerAdapter(getChildFragmentManager(), category, newsListCat);
        viewPager.setAdapter(adapter);

        if (selectedNews != null) {
            viewPager.setCurrentItem(newsListCat.indexOf(selectedNews));
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (tablet) {
                    News news = newsListCat.get(position);
                    notifyNewsSelected(news);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("selectedNews", selectedNews.getNumber());
    }

    public News getSelectedNews() {
        return selectedNews;
    }

    public void setSelectedNews(News selectedNews) {
        this.selectedNews = selectedNews;
        final List<News> newsListCat = newsList.getNewsList();
        if (selectedNews != null && viewPager != null) {
            viewPager.setCurrentItem(newsListCat.indexOf(selectedNews));
        }
    }

    // добавлено (начало)


    @Override
    public void onResume() {
        super.onResume();
        selectedNews.addListener(this);
        selectedNews.update(selectedNews.getNumber());
    }

    @Override
    public void updateStarted() {

    }

    @Override
    public void updateFinished(boolean success) {
        if(success){
            adapter.setNewsList(newsList.getNewsList());
        }
        else {
            Toast.makeText(getActivity(), com.example.news.R.string.error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        selectedNews.removeListener(this);
    }

    // добавлено (конец)
}

