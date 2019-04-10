package com.example.inved.mynews;

import android.content.Intent;

import com.example.inved.mynews.controller.SearchActivity;
import com.example.inved.mynews.controller.SearchResultActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import static org.junit.Assert.assertEquals;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class SearchActivityTest {

    @Test
    public void clickingSearchButton_ShouldStartSearchResultActivity(){
        SearchActivity searchActivity = Robolectric.setupActivity(SearchActivity.class);
        searchActivity.findViewById(R.id.button_Search).performClick();

        Intent expectedIntent = new Intent (searchActivity, SearchResultActivity.class);
        Intent actual = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();

        assertEquals(expectedIntent.getComponent(),actual.getComponent());
    }
}
