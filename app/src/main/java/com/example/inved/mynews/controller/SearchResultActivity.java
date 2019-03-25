package com.example.inved.mynews.controller;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.inved.mynews.R;
import com.example.inved.mynews.searchapi.Doc;

import java.util.ArrayList;
import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SearchResultActivity extends AppCompatActivity {

    RecyclerViewSearchAdapter mRecyclerViewSearchAdapter;

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
            Intent intent = getIntent();
            ArrayList<Doc> docArrayList = intent.getParcelableArrayListExtra(SearchActivity.KEY_LIST_DOC);
            mRecyclerViewSearchAdapter.setData(docArrayList);


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void configureToolbar(){
        // Get the toolbar view inside the activity layout
        Toolbar toolbar = findViewById(R.id.toolbar);
        // Sets the Toolbar
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Search results");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
