package com.example.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class CategoriesFragment extends CategorySelectingFragment implements CategoriesList.OnUpdateListener {

    private RecyclerView recyclerViewCategories;
    private CategoriesAdapter adapter;

    private CategoriesList categoriesList;
    private Category selectedCategory;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoriesList = CategoriesList.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(com.example.news.R.layout.categories_fragment, container, false);

        recyclerViewCategories = (RecyclerView)view.findViewById(com.example.news.R.id.recyclerViewCategories);
        recyclerViewCategories.setLayoutManager(new GridLayoutManager(getContext(),2));

        adapter = new CategoriesAdapter(categoriesList.getCategories());

        adapter.setListener(new OnCategorySelectedListener() {
            @Override
            public void onCategorySelected(Category category) {
                notifyCategorySelected(category);
            }
        });
        recyclerViewCategories.setAdapter(adapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        categoriesList.addListener(this);
        categoriesList.update();
    }

    @Override
    public void updateStarted() {

    }

    @Override
    public void updateFinished(boolean success) {
        if (success) {
            adapter.setCategories(categoriesList.getCategories());
        } else {
            Toast.makeText(getActivity(), com.example.news.R.string.error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        categoriesList.removeListener(this);
    }

    public Category getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(Category selectedCategory) {
        this.selectedCategory = selectedCategory;
        adapter.setSelectedCategory(selectedCategory);
    }


}


