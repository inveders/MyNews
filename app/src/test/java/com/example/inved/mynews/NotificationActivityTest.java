package com.example.inved.mynews;


import android.widget.CheckBox;
import android.widget.Switch;

import com.example.inved.mynews.brain.SearchBrain;
import com.example.inved.mynews.notifications.NotificationActivity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;


@RunWith(RobolectricTestRunner.class)
@Config(sdk = {27})
public class NotificationActivityTest {

    private NotificationActivity notificationActivity;
    private SearchBrain spySearchBrain;
    private CheckBox checkboxTechnology;
    private CheckBox checkboxWorld;
    private List<String> isCheckBoxList = new ArrayList<>();
    private Switch notificationSwitchEnableTest;

    @Before
    public void setUp() {
        notificationActivity = Robolectric.buildActivity(NotificationActivity.class)
                .create()
                .resume()
                .get();

        spySearchBrain = Mockito.spy(new SearchBrain()); //Utiliser cette notation

        checkboxTechnology = notificationActivity.findViewById(R.id.checkBox_technology);
        checkboxWorld = notificationActivity.findViewById(R.id.checkBox_world);
        notificationSwitchEnableTest = notificationActivity.findViewById(R.id.notification_switch);


    }

    @Test
    public void checkingTechnologyAndWorldCheckboxes_ShouldCreateGoodLucene() {

        //Given

        checkboxTechnology.setChecked(true);
        checkboxWorld.setChecked(true);

        isCheckBoxList.add("Technology");
        isCheckBoxList.add("World");

        //When
        String lucene = spySearchBrain.getLucene(isCheckBoxList);

        //Then
        Assert.assertEquals("section_name:(\"Technology\"\"World\")", lucene);

    }

    @Test
    public void enableNotificationSwitch_ShouldFillCheckboxlist() {

        //Given
        notificationSwitchEnableTest.setEnabled(true);
        checkboxTechnology.setChecked(true);
        checkboxWorld.setChecked(true);

        //When
        isCheckBoxList = notificationActivity.fillCheckboxList();
        boolean expected1 = isCheckBoxList.contains("Technology");
        boolean expected2 = isCheckBoxList.contains("World");
        boolean expected3 = isCheckBoxList.contains("Sport");

        //Then
        Assert.assertTrue(expected1);
        Assert.assertTrue(expected2);
        Assert.assertFalse(expected3);

    }

}

