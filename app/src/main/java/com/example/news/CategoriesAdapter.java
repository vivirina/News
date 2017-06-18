package com.example.news;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesListCell> {

    private List<Category> categories;
    private OnCategorySelectedListener listener;
    private  Category selectedCategory;

    public CategoriesAdapter(List<Category> categories){
        this.categories = new ArrayList<>(categories);
    }

    public List<Category> getCategories(){
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
        notifyDataSetChanged();      // добавлено
    }

    public OnCategorySelectedListener getListener() {
        return listener;
    }

    public void setListener(OnCategorySelectedListener listener) {
        this.listener = listener;
    }

    @Override
    public CategoriesListCell onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(com.example.news.R.layout.categories_list_cell, parent, false);
        return new CategoriesListCell (itemView, listener);
    }

    @Override
    public int getItemCount(){
        return categories.size();
    }

    @Override
    public void onBindViewHolder(CategoriesListCell holder, int position){
        holder.setCategory(categories.get(position));
    }

    class CategoriesListCell extends RecyclerView.ViewHolder{

        private Category category;

        private TextView nameCategory;

        public CategoriesListCell(View itemView, final OnCategorySelectedListener listener){
            super(itemView);

            nameCategory = (TextView) itemView.findViewById(com.example.news.R.id.shortName);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    if(listener != null){
                        listener.onCategorySelected(category);
                    }
                }
            });

        }

        public Category getCategory() {
            return category;
        }

        public void setCategory(Category category) {
            this.category = category;

            nameCategory.setText(category.getShortName());

            if (selectedCategory != null && this.category.getId() == selectedCategory.getId()){
                itemView.setSelected(true);
            }
            else {
                itemView.setSelected(false);
            }
        }
    }

    public Category getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(Category selectedCategory) {
        this.selectedCategory = selectedCategory;
        notifyDataSetChanged();
    }
}

