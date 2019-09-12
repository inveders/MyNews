package com.example.inved.mynews;

import android.content.Intent;
import android.view.View;

import com.example.inved.mynews.controller.MainActivity;
import com.example.inved.mynews.controller.SearchActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static org.junit.Assert.assertEquals;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {28})
public class MainActivityTest {

    private MainActivity mainActivity;

    @Before
    public void setUp() {
        mainActivity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .visible()
                .get();
    }

    @Test
    public void clickingMainSearchIcon_ShouldStartSearchActivity() {

        View searchIcon = mainActivity.findViewById(R.id.menu_activity_main_search);
        searchIcon.performClick();

        ShadowActivity shadow = shadowOf(mainActivity);
        Intent expectedIntent = new Intent(mainActivity, SearchActivity.class);
        Intent actual =shadow.getNextStartedActivity();

        assertEquals(expectedIntent.getComponent(), actual.getComponent());

    }


}
