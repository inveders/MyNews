package com.example.inved.mynews;


import com.example.inved.mynews.controller.NotificationActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class NotificationActivityTest {

    NotificationActivity notificationActivity;
    private boolean notificationSwitchEnableTest;

      @Test
    public void clickingSwitchButton_ShouldEnabledSwitchButton(){

        notificationSwitchEnableTest=notificationActivity.findViewById(R.id.notification_switch).performClick();
      //  notificationSwitchEnableTest.performClick();

        assertEquals(notificationSwitchEnableTest,true);
    }
}
