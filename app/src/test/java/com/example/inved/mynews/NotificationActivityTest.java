package com.example.inved.mynews;


import android.widget.CheckBox;
import android.widget.Switch;

import com.example.inved.mynews.brain.SearchBrain;
import com.example.inved.mynews.controller.NotificationActivity;

import junit.framework.Assert;

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
        notificationActivity = Mockito.spy(Robolectric.buildActivity(NotificationActivity.class)
                .create()
                .resume()
                .get());

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

    //   @Test
 /*   public void checkingTechnologyAndWorldCheckboxes_ShouldSendGoodArgumentsWithScheduleJobSuccess() {

        //Given
        CheckBox checkboxTechnology = notificationActivity.findViewById(R.id.checkBox_technology);
        CheckBox checkboxWorld = notificationActivity.findViewById(R.id.checkBox_world);
        Switch notificationSwitchEnableTest = notificationActivity.findViewById(R.id.notification_switch);
        EditText editTextSearch = notificationActivity.findViewById(R.id.text_input_layout);

        checkboxTechnology.setChecked(true);
        checkboxWorld.setChecked(true);
        editTextSearch.setText("Plane");
        notificationSwitchEnableTest.setEnabled(true);

        //When implicite

        //Then

        Mockito.verify(notificationActivity).firstScheduleJob(any(JobInfo.class));//On teste si notre activité appelle firstScheduleJob
        Mockito.verify(notificationActivity).firstScheduleJob(argThat(new ArgumentMatcher<JobInfo>() {
            @Override
            public boolean matches(JobInfo argument) {
                if (argument.isRequireCharging()) {
                    return false;
                }

                if (argument.getFlexMillis()!=60*24*1000){
                    return false;
                }

                if (argument.getRequiredNetwork().equals(JobInfo.NETWORK_TYPE_NONE)){
                    return false;
                }

                if(!argument.getExtras().getString(KEY_FILTER_BUNDLE).equals("section_name:(\"Technology\"\"World\")")){
                    return false;
                }

                if(!argument.getExtras().getString(KEY_QUERY_BUNDLE).equals("Plane")){
                    return false;
                }

                return true;
            }
        }));
    }*/
}

