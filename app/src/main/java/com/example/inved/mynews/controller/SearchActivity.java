package com.example.inved.mynews.controller;



import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.inved.mynews.R;
import com.example.inved.mynews.brain.SearchBrain;
import com.example.inved.mynews.searchapi.SearchResult;
import com.example.inved.mynews.utils.MyAsyncTaskLoaderSearch;

import java.util.List;

public class SearchActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<SearchResult>{

    EditText editTextSearch;
    CheckBox checkboxTechnology,checkboxScience,checkboxSports,checkboxFood,checkboxTravel,checkboxWorld;
    Button buttonSearch;
    SearchBrain searchBrain;
    String userInput, mQuery;
    List<String> isCheckBoxList, mFilter;
    LoaderManager mLoaderManager;
    RecyclerViewSearchAdapter mRecyclerViewSearchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        editTextSearch = findViewById(R.id.text_input);
        checkboxTechnology = findViewById(R.id.checkBox_technology);
        checkboxScience = findViewById(R.id.checkBox_science);
        checkboxSports = findViewById(R.id.checkBox_sports);
        checkboxFood = findViewById(R.id.checkBox_food);
        checkboxTravel = findViewById(R.id.checkBox_travel);
        checkboxWorld = findViewById(R.id.checkBox_world);
        buttonSearch = findViewById(R.id.button_Search);


        //In this part of the code, we show a Toast when we click on the search button
        buttonSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                onCheckboxClicked();
                /**Convert an EditText in String*/
                userInput = editTextSearch.getText().toString();
                mFilter = isCheckBoxList;

                if(mFilter!=null && !TextUtils.isEmpty(userInput) || !TextUtils.isEmpty(userInput)) {
                    searchBrain = new SearchBrain();
                    mQuery = searchBrain.getLucene(userInput); //to have the query
                    //LoaderManager initialization
                    mLoaderManager = getSupportLoaderManager();
                    if (mLoaderManager.getLoader(1) != null) {
                        mLoaderManager.initLoader(1, null, SearchActivity.this);
                    }

                    //Launch of the asynctaskLoaderSearch
                    startAsyncTaskLoaderSearch();

                    // Toast.makeText(SearchActivity.this,userInput,Toast.LENGTH_SHORT).show();

                    Toast.makeText(SearchActivity.this, "ok", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(userInput)) {
                    editTextSearch.setError("Rentrez au moins un mot clé");
                }
                else {
                    Toast.makeText(SearchActivity.this, "Aucune Checkbox n'est cochée", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void onCheckboxClicked() {

        //Delete all elements of the List before to verify is checkbox are checked.
       // isCheckBoxList.clear();
        // Check which checkbox was clicked
        if (checkboxTechnology.isChecked()) isCheckBoxList.add("Technology");
        if (checkboxScience.isChecked()) isCheckBoxList.add("Science");
        if (checkboxSports.isChecked()) isCheckBoxList.add("Sports");
        if (checkboxFood.isChecked()) isCheckBoxList.add("Food");
        if (checkboxTravel.isChecked()) isCheckBoxList.add("Travel");
        if (checkboxWorld.isChecked()) isCheckBoxList.add("World");

    }

    /**Start a new AsyncTaskLoader*/
    private void startAsyncTaskLoaderSearch(){

        mLoaderManager.initLoader(1,null,this);
    }

    @NonNull
    @Override
    public Loader<SearchResult> onCreateLoader(int i, Bundle bundle) {
        return new MyAsyncTaskLoaderSearch(this,mQuery,mFilter);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<SearchResult> loader, SearchResult data) {

       // mRecyclerViewSearchAdapter.setData(data);
    }

    @Override
    public void onLoaderReset(Loader<SearchResult> loader) {

    }

}
