package com.example.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by Ирина on 01.06.2017.
 */

public abstract class NewsSelectingFragment extends Fragment{

    private OnNewsSelectedListener listener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getActivity() instanceof OnNewsSelectedListener) {
            listener = (OnNewsSelectedListener) getActivity();
        } else {
            throw new RuntimeException(getActivity().getClass().getSimpleName() + " does not implement " + OnNewsSelectedListener.class.getSimpleName());
        }
    }

    protected void notifyNewsSelected(News news) {
        if (listener != null) {
            listener.onNewsSelected(news);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        listener = null;
    }
}

