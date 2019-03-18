package com.example.inved.mynews.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.inved.mynews.R;

public class SearchResultActivity extends AppCompatActivity {

    RecyclerViewSearchAdapter mRecyclerViewSearchAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_result_search);

            RecyclerView recyclerView = findViewById(R.id.search_recycler_view);
            mRecyclerViewSearchAdapter = new RecyclerViewSearchAdapter();
            recyclerView.setAdapter(mRecyclerViewSearchAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));


            Intent intent = getIntent();
            String text = intent.getStringExtra(Intent.EXTRA_TEXT);
            TextView textView = findViewById(R.id.textViewSearchResult);
            textView.setText(text);
    }
}
