package com.example.inved.mynews;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;

import com.example.inved.mynews.controller.MainActivity;
import com.example.inved.mynews.controller.NotificationActivity;
import com.example.inved.mynews.controller.SearchActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static org.junit.Assert.assertEquals;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
@Config (sdk={27})
public class MainActivityTest {


    @Test
    public void clickingMainSearchIcon_ShouldStartSearchActivity(){
        MainActivity mainActivity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .visible()
                .get();
        View searchIcon = mainActivity.findViewById(R.id.menu_activity_main_search);
        searchIcon.performClick();

        Intent expectedIntent = new Intent (mainActivity, SearchActivity.class);
        Intent actual = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();

        assertEquals(expectedIntent.getComponent(),actual.getComponent());
    }


    @Test
    public void clickingMainParamIcon_ShouldStartNotificationActivity(){
        MainActivity mainActivity = Robolectric.buildActivity(MainActivity.class)
                .create()
                .visible()
                .get();
        ShadowActivity shadowActivity = shadowOf(mainActivity);
        MenuItem menuIcon = shadowActivity.getOptionsMenu().findItem(R.id.menu_activity_main_params);
        menuIcon.getActionView().performClick();
        Intent expectedIntent = new Intent (mainActivity, NotificationActivity.class);
        Intent actual = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();

        assertEquals(expectedIntent.getComponent(),actual.getComponent());
    }


}
