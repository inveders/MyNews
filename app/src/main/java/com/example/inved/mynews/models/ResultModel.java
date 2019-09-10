package com.example.inved.mynews.models;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.inved.mynews.retrofit.RepositoryMostPopular;
import com.example.inved.mynews.retrofit.RepositorySearch;
import com.example.inved.mynews.retrofit.RepositoryTopStories;
import com.example.inved.mynews.searchapi.Doc;
import com.example.inved.mynews.topstoriesapi.Result;

import java.util.List;

public class ResultModel extends AndroidViewModel {

    private RepositoryTopStories repositoryTopStories;
    private RepositoryMostPopular repositoryMostPopular;
    private RepositorySearch repositorySearch;


    public ResultModel(@NonNull Application application) {
        super(application);
        repositoryTopStories = new RepositoryTopStories();
        repositoryMostPopular = new RepositoryMostPopular(application);
        repositorySearch = new RepositorySearch();
    }


    public LiveData<List<Result>> getAllResultsTopStories(String name) {
        return repositoryTopStories.getMutableLiveData(name);
    }


    public LiveData<List<Result>> getAllResultsMostPopular(String name) {
        return repositoryMostPopular.getMutableLiveData(name);
    }

    public LiveData<List<Doc>> getAllSearchResults(String query, String filter, String beginDate, String endDate) {
        return repositorySearch.getMutableLiveData(query,filter,beginDate,endDate);
    }

}
