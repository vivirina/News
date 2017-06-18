package com.example.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by Ирина on 01.06.2017.
 */

public abstract class CategorySelectingFragment extends Fragment{

    private OnCategorySelectedListener listener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getActivity() instanceof OnCategorySelectedListener) {
            listener = (OnCategorySelectedListener) getActivity();
        } else {
            throw new RuntimeException(getActivity().getClass().getSimpleName() + " does not implement " + OnCategorySelectedListener.class.getSimpleName());
        }
    }

    protected void notifyCategorySelected(Category category) {
        if (listener != null) {
            listener.onCategorySelected(category);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        listener = null;
    }
}


