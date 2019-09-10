package com.example.inved.mynews.retrofit;

import android.content.Intent;

import androidx.lifecycle.MutableLiveData;

import com.example.inved.mynews.MainApplication;
import com.example.inved.mynews.controller.SearchResultActivity;
import com.example.inved.mynews.searchapi.Doc;
import com.example.inved.mynews.searchapi.SearchResult;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.inved.mynews.controller.AbsNyTimesFragment.API_KEY;

public class RepositorySearch {

    private static final String KEY_LIST_DOC="KEY_LIST_DOC" ;
    private static final String KEY_LIST_NUMBER="KEY_LIST_NUMBER";

    private ArrayList<Doc> results = new ArrayList<>();
    private MutableLiveData<List<Doc>> mutableLiveData = new MutableLiveData<>();

    public RepositorySearch() {
    }

    public MutableLiveData<List<Doc>> getMutableLiveData(String query,String filter,String beginDate,String endDate) {

        NyTimesSearchAPI nyTimesSearchAPI = RetrofitServiceSearch.getApiServiceSearch();

        Call<SearchResult> call = nyTimesSearchAPI.getNyTimesSearchAPI(query, filter, beginDate, endDate, API_KEY);

        call.enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                SearchResult searchResult = response.body();

                if(searchResult!=null && searchResult.response!=null && searchResult.response.docs!=null){
                    //  Log.d("DEBAGaa", "Nombre de résultat "+data.response.docs.size());
                    Intent intent = new Intent (MainApplication.getInstance().getApplicationContext(), SearchResultActivity.class);
                    intent.putParcelableArrayListExtra(KEY_LIST_DOC,searchResult.response.docs);
                    intent.putExtra(KEY_LIST_NUMBER,searchResult.response.docs.size());
                    results = searchResult.response.docs;
                    mutableLiveData.setValue(results);
                    MainApplication.getInstance().getApplicationContext().startActivity(intent);
                }


            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {

            }
        });

        return mutableLiveData;

    }
}
