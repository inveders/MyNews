package com.example.inved.mynews.controller.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inved.mynews.R;
import com.example.inved.mynews.models.MemorizedArticlesViewModel;
import com.example.inved.mynews.models.ResultModel;
import com.example.inved.mynews.topstoriesapi.Result;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public abstract class AbsNyTimesFragment extends Fragment {

    static final String KEY_ARG_SECTION = "KEY_ARG_SECTION";
    static final String KEY_ARG_PERIOD = "KEY_ARG_PERIOD";
    private static final int DATABASE_MAX_SIZE_BEFORE_DELETING_DATA = 30;
    private static final int DATABASE_NUMBER_DATA_TO_DELETE = 10;

    private RecyclerViewAdapter mRecyclerViewAdapter;
    private ResultModel resultModel;
    private MemorizedArticlesViewModel memorizedArticlesViewModel;

    public static final String API_KEY = "69b33155fef846e29c9753f95e628397";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        memorizedArticlesViewModel = ViewModelProviders.of(this).get(MemorizedArticlesViewModel.class);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_general, container, false);

        //RecyclerView initialization
        RecyclerView mRecyclerView = v.findViewById(R.id.fragment_general_recycler_view);
        mRecyclerViewAdapter = new RecyclerViewAdapter(getContext());
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        //Choose how to display the list in the RecyclerView (vertical or horizontal)
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL));

        //Assign the value to declared resultModel variable
        resultModel = ViewModelProviders.of(this).get(ResultModel.class);

        this.liveDataObservers();

        return v;
    }

    public abstract String getTitle();

    public abstract boolean isMostPopular();


    private void liveDataObservers() {

        if (isMostPopular()) {
            resultModel.getAllResultsMostPopular(getTitle()).observe(this, results -> {

                searchMemorizedArticles(results);
                //Update the data to adapter
                mRecyclerViewAdapter.setData(results);
                //Update to the UI with latest data

            });
        } else {
            resultModel.getAllResultsTopStories(getTitle()).observe(this, results -> {

                searchMemorizedArticles(results);
                //Update the data to adapter
                mRecyclerViewAdapter.setData(results);

            });
        }


    }


    private void searchMemorizedArticles(List<Result> results) {

        memorizedArticlesViewModel.getMemorizedArticlesList().observe(this, memorizedArticles -> {

            Set<String> listUrl = new HashSet<>();
            Set<String> listCommonUrl = new HashSet<>();

            if (memorizedArticles != null) {

                // Put memorized articles in hashset
                for (int i = 0; i < memorizedArticles.size(); i++) {
                    listUrl.add(memorizedArticles.get(i).getUrl());
                }

                // Check if articles from retrofit exist in my database

                if (results != null) {
                    for (int i = 0; i < results.size(); i++) {
                        if (listUrl.contains(results.get(i).url)) {
                            listCommonUrl.add(results.get(i).url);

                            checkDatabaseSize();
                        }
                    }
                    mRecyclerViewAdapter.setArticleMemorized(listCommonUrl);

                    mRecyclerViewAdapter.setData(results);
                }
            }
        });
    }

    private void checkDatabaseSize() {
        if (memorizedArticlesViewModel != null) {

            if(memorizedArticlesViewModel.getSizeDatabase()>=DATABASE_MAX_SIZE_BEFORE_DELETING_DATA){

                int id = memorizedArticlesViewModel.getId();


                for (int i = id-DATABASE_MAX_SIZE_BEFORE_DELETING_DATA; i <
                        id-(DATABASE_MAX_SIZE_BEFORE_DELETING_DATA-DATABASE_NUMBER_DATA_TO_DELETE); i++) {
                    memorizedArticlesViewModel.deleteArticle(i);
                }


            }

        }
    }

}




