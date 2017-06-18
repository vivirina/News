package com.example.news;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

public class CategoryCell extends ConstraintLayout {

    private Category category;

    private TextView nameCategoryView;

    public CategoryCell(Context context, AttributeSet attrs){
        super(context, attrs);
        LayoutInflater.from(context).inflate(com.example.news.R.layout.category_cell,this);
    }

    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();

        if (isInEditMode()){
            return;
        }

        nameCategoryView = (TextView) findViewById(com.example.news.R.id.shortName);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;

        nameCategoryView.setText(category.getShortName());
    }
}

