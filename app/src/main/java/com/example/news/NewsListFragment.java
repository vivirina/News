package com.example.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class NewsListFragment extends NewsSelectingFragment implements NewsList.OnUpdateListener {

    public static final String INTENT_EXTRA_CATEGORY_NUMBER = "number";

    private RecyclerView recyclerViewNewsList;
    private NewsListAdapter adapter;

    private NewsList newsList;      // добавлено
    private News selectedNews;
    private int idCategory;         // добавлено
    private Category category;

    // добавлен метод
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        idCategory = getArguments().getInt(INTENT_EXTRA_CATEGORY_NUMBER, 0);
        category = CategoriesList.getInstance().getCategory(idCategory);
        newsList = NewsList.getInstance(category);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(com.example.news.R.layout.news_list_fragment, container, false);

        recyclerViewNewsList = (RecyclerView) view.findViewById(com.example.news.R.id.recyclerViewNewsList);
        recyclerViewNewsList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new NewsListAdapter(newsList.getNewsList());

        adapter.setListener(new OnNewsSelectedListener() {
            @Override
            public void onNewsSelected(News news) {
                notifyNewsSelected(news);
            }
        });
        recyclerViewNewsList.setAdapter(adapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        newsList.addListener(this);
        newsList.update(idCategory);
    }

    @Override
    public void updateStarted() {

    }

    @Override
    public void updateFinished(boolean success) {
        if (success) {
            adapter.setNewsList(newsList.getNewsList());

            // добавлено (начало)
            News news = newsList.getNewsList().get(0);
            NewsesPagerFragments pagerFragmentNewses = new NewsesPagerFragments();
            Bundle argumentsNewses = new Bundle();
            argumentsNewses.putInt(NewsesPagerFragments.INTENT_EXTRA_NEWS_NUMBER, news.getNumber());
            pagerFragmentNewses.setArguments(argumentsNewses);
            FragmentManager fragmentManager = getFragmentManager();
            //fragmentManager.beginTransaction().replace(com.example.news.R.id.rightContainer, pagerFragmentNewses).commit();
            //fragmentManager.beginTransaction().add(com.example.news.R.id.rightContainer, pagerFragmentNewses).commit();
            fragmentManager.beginTransaction().addToBackStack(null).replace(com.example.news.R.id.rightContainer, pagerFragmentNewses).commit();
            // добавлено (конец)

        } else {
            Toast.makeText(getActivity(), com.example.news.R.string.error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        newsList.removeListener(this);
    }

    public News getSelectedNews() {
        return selectedNews;
    }

    public void setSelectedNews(News selectedNews) {
        this.selectedNews = selectedNews;
        adapter.setSelectedNews(selectedNews);
    }
}




