package com.example.inved.mynews.controller;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inved.mynews.R;
import com.example.inved.mynews.models.ResultModel;

import java.util.Objects;

import static com.example.inved.mynews.controller.SearchActivity.KEY_DATA_BEGIN_DATE;
import static com.example.inved.mynews.controller.SearchActivity.KEY_DATA_END_DATE;
import static com.example.inved.mynews.controller.SearchActivity.KEY_DATA_FILTER;
import static com.example.inved.mynews.controller.SearchActivity.KEY_DATA_QUERY;

public class SearchResultActivity extends AppCompatActivity {

    RecyclerViewSearchAdapter mRecyclerViewSearchAdapter;
    ResultModel resultModel;

    String mQuery;
    String mFilter;
    String mBeginDate;
    String mEndDate;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_search);
        this.configureToolbar();

        RecyclerView recyclerView = findViewById(R.id.search_recycler_view);
        mRecyclerViewSearchAdapter = new RecyclerViewSearchAdapter();
        recyclerView.setAdapter(mRecyclerViewSearchAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));


        Intent intent = getIntent();
        mQuery = intent.getStringExtra(KEY_DATA_QUERY);
        mFilter = intent.getStringExtra(KEY_DATA_FILTER);
        mBeginDate = intent.getStringExtra(KEY_DATA_BEGIN_DATE);
        mEndDate = intent.getStringExtra(KEY_DATA_END_DATE);


        //Assign the value to declared resultModel variable
        resultModel = ViewModelProviders.of(this).get(ResultModel.class);
        liveDataObservers(mQuery, mFilter, mBeginDate, mEndDate);


    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void configureToolbar() {
        // Get the toolbar view inside the activity layout
        Toolbar toolbar = findViewById(R.id.toolbar);
        // Sets the Toolbar
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.titlePageSearchResult));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void liveDataObservers(String mQuery, String mFilter, String mBeginDate, String mEndDate) {

        Log.d("SearchActivity:", "Data query " + mQuery + " filter: " + mFilter + " mBeginDate " + mBeginDate + " mEndDate" + mEndDate);

        resultModel.getAllSearchResults(mQuery, mFilter, mBeginDate, mEndDate).observe(this, searchResults -> {

            int number_result = searchResults.size();
            if (number_result == 0) {
                Toast.makeText(SearchResultActivity.this, getString(R.string.no_result), Toast.LENGTH_SHORT).show();
            }

            mRecyclerViewSearchAdapter.setData(searchResults);
            mRecyclerViewSearchAdapter.notifyDataSetChanged();

            Log.d("SearchActivityResult:", "Data has updated");
        });
    }
}
