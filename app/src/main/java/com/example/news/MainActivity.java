package com.example.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Ирина on 01.06.2017.
 */

public class MainActivity extends AppCompatActivity implements OnCategorySelectedListener, OnNewsSelectedListener {

    private boolean tablet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.news.R.layout.main_activity);

        tablet = getResources().getBoolean(com.example.news.R.bool.tablet);

        if (savedInstanceState == null) {
            if (tablet) {

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                CategoriesFragment listFragment = new CategoriesFragment();
                transaction.add(com.example.news.R.id.leftContainer, listFragment);

                transaction.commit();
            } else {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                CategoriesFragment listFragment = new CategoriesFragment();
                transaction.add(com.example.news.R.id.mainContainer, listFragment);

                transaction.commit();
            }
        }
    }

    @Override
    public void onCategorySelected(Category category) {
        if (tablet) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            NewsListFragment pagerFragmentCat = new NewsListFragment();
            Bundle argumentsCat = new Bundle();
            argumentsCat.putInt(NewsListFragment.INTENT_EXTRA_CATEGORY_NUMBER, category.getId());
            pagerFragmentCat.setArguments(argumentsCat);
            transaction.addToBackStack(null).replace(com.example.news.R.id.leftContainer, pagerFragmentCat);
/*
            News news = NewsList.getInstance(category).getNewsList().get(0);
            NewsesPagerFragments pagerFragmentNewses = new NewsesPagerFragments();
            Bundle argumentsNewses = new Bundle();
            argumentsNewses.putInt(NewsesPagerFragments.INTENT_EXTRA_CATEGORY_ID,category.getId());
            argumentsNewses.putInt(NewsesPagerFragments.INTENT_EXTRA_NEWS_NUMBER,news.getNumber());
            argumentsNewses.putInt(NewsInfoFragment.INTENT_EXTRA_NEWS_NUMBER, news.getNumber());
            pagerFragmentNewses.setArguments(argumentsNewses);
            transaction.add(com.example.news.R.id.rightContainer, pagerFragmentNewses);
*/
            transaction.commit();

        } else {

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            NewsListFragment pagerFragment = new NewsListFragment();
            Bundle arguments = new Bundle();
            arguments.putInt(NewsListFragment.INTENT_EXTRA_CATEGORY_NUMBER, category.getId());
            pagerFragment.setArguments(arguments);

            transaction.addToBackStack(null).replace(com.example.news.R.id.mainContainer, pagerFragment);

            transaction.commit();
        }
    }

    @Override
    public void onNewsSelected(News news) {
        if (tablet) {
            NewsListFragment listFragment = (NewsListFragment) getSupportFragmentManager().findFragmentById(com.example.news.R.id.leftContainer);
            listFragment.setSelectedNews(news);

            NewsesPagerFragments pagerFragment = (NewsesPagerFragments) getSupportFragmentManager().findFragmentById(com.example.news.R.id.rightContainer);
            pagerFragment.setSelectedNews(news);

        } else {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            NewsesPagerFragments pagerFragment = new NewsesPagerFragments();
            Bundle arguments = new Bundle();
            arguments.putInt(NewsesPagerFragments.INTENT_EXTRA_CATEGORY_ID, news.getCategory().getId());
            arguments.putInt(NewsesPagerFragments.INTENT_EXTRA_NEWS_NUMBER, news.getNumber());
            pagerFragment.setArguments(arguments);

            transaction.addToBackStack(null).replace(com.example.news.R.id.mainContainer, pagerFragment);

            transaction.commit();
        }
    }
}





