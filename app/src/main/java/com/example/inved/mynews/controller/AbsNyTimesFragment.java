package com.example.inved.mynews.controller;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.loader.app.LoaderManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inved.mynews.R;
import com.example.inved.mynews.models.ResultModel;
import com.example.inved.mynews.topstoriesapi.Result;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */


public abstract class AbsNyTimesFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Result>> {

    static final String KEY_ARG_SECTION = "KEY_ARG_SECTION";
    static final String KEY_ARG_PERIOD = "KEY_ARG_PERIOD";

    private RecyclerViewAdapter mRecyclerViewAdapter;
    private ResultModel resultModel;

    public static final String API_KEY = "69b33155fef846e29c9753f95e628397";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_general, container, false);

        //RecyclerView initialization
        RecyclerView mRecyclerView = v.findViewById(R.id.fragment_general_recycler_view);
        mRecyclerViewAdapter = new RecyclerViewAdapter();
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        //Choose how to display the list in the RecyclerView (vertical or horizontal)
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(),DividerItemDecoration.VERTICAL));

        //Assign the value to declared resultModel variable
        resultModel = ViewModelProviders.of(this).get(ResultModel.class);
        this.liveDataObservers();

        return v;
    }



    public abstract String getTitle();

    public abstract boolean isMostPopular();


    private void liveDataObservers() {


        if(isMostPopular()){
            resultModel.getAllResultsMostPopular(getTitle()).observe(this, results -> {

                //Update the data to adapter
                mRecyclerViewAdapter.setData(results);
                //Update to the UI with latest data
                mRecyclerViewAdapter.notifyDataSetChanged();

                Log.d("Debago", "AbsTimeFragment: getTitle "+getTitle());
            });
        }else {
            resultModel.getAllResultsTopStories(getTitle()).observe(this, results -> {

          /*      Set<String> listCommonUrl = new HashSet<>();
                listCommonUrl.clear();
                //  Log.d("DEBAGO", "2. result list " + results);
                if (results != null) {
                    for (int i = 0; i < results.size(); i++) {
                        if (!MemorizedArticlesDatabase.getInstance(getContext()).memorizedArticlesDao().getMemorizedArticles(results.get(i).url).isEmpty()) {
                            listCommonUrl.add(results.get(i).url);
                        }else{
                            Log.d("DEBAGO", "article "+i+" n'est pas dans la bdd ");
                        }

                    }
                }
                //Set Memorized articles in Recycler view to change their color
                mRecyclerViewAdapter.setArticleMemorized(listCommonUrl);*/
                //Update the data to adapter
                mRecyclerViewAdapter.setData(results);
                //Update to the UI with latest data
                mRecyclerViewAdapter.notifyDataSetChanged();

                Log.d("Debago", "AbsTimeFragment: getTitle "+getTitle());
            });
        }


    }




}




