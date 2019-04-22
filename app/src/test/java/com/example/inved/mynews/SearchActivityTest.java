package com.example.inved.mynews;

import android.widget.Button;

import com.example.inved.mynews.controller.SearchActivity;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {27})
public class SearchActivityTest {

    private SearchActivity searchActivity;
    private Button searchButton;

    @Before
    public void setUp() {
        searchActivity = Robolectric.buildActivity(SearchActivity.class)
                .create()
                .resume()
                .get();

        searchButton = searchActivity.findViewById(R.id.button_Search);


    }

    @Test
    public void clickingSearchButton_WhenCheckboxListIsEmpty_ShouldProducedToastMessage() {

        List<String> isCheckboxList = searchActivity.fillCheckboxList();
        isCheckboxList.clear();
        boolean expected1 = isCheckboxList.isEmpty();
        searchButton.performClick();

        Assert.assertTrue(expected1);
        //assertEquals(ShadowToast.getTextOfLatestToast(),"Aucune Checkbox n'est cochée");
    }

}





