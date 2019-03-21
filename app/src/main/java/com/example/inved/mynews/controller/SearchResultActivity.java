package com.example.inved.mynews.controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.inved.mynews.R;
import com.example.inved.mynews.searchapi.Doc;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
            ArrayList<Doc> docArrayList = intent.getParcelableArrayListExtra(SearchActivity.KEY_LIST_DOC);
            mRecyclerViewSearchAdapter.setData(docArrayList);

    }
}
