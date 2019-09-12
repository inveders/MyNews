package com.example.inved.mynews;

import android.widget.Button;
import android.widget.EditText;

import com.example.inved.mynews.controller.activity.SearchActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {27})
public class SearchActivityTest {

    private Button searchButton;
    private EditText editTextSearch;

    @Before
    public void setUp() {
        SearchActivity searchActivity = Robolectric.buildActivity(SearchActivity.class)
                .create()
                .resume()
                .get();

        searchButton = searchActivity.findViewById(R.id.button_Search);
        editTextSearch = searchActivity.findViewById(R.id.text_input_layout);

    }

    @Test
    public void clickingSearchButton_WhenCheckboxListIsEmpty_ShouldProducedToastMessage() {

        editTextSearch.setText("Planes");
        searchButton.performClick();


        assertEquals("Aucune Checkbox n'est cochée",ShadowToast.getTextOfLatestToast());
    }

}





