package com.example.inved.mynews;

import android.content.Intent;

import com.example.inved.mynews.controller.MainActivity;
import com.example.inved.mynews.controller.NotificationActivity;
import com.example.inved.mynews.controller.SearchActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import static org.junit.Assert.assertEquals;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)

public class MainActivityTest {

    @Test
    public void clickingMainSearchButton_ShouldStartSearchActivity(){
        MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);
        mainActivity.findViewById(R.id.menu_activity_main_search).performClick();

        Intent expectedIntent = new Intent (mainActivity, SearchActivity.class);
        Intent actual = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();

        assertEquals(expectedIntent.getComponent(),actual.getComponent());
    }


    @Test
    public void clickingMainParamButton_ShouldStartNotificationActivity(){
        MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);
        mainActivity.findViewById(R.id.menu_activity_main_params).performClick();

        Intent expectedIntent = new Intent (mainActivity, NotificationActivity.class);
        Intent actual = shadowOf(RuntimeEnvironment.application).getNextStartedActivity();

        assertEquals(expectedIntent.getComponent(),actual.getComponent());
    }
}
