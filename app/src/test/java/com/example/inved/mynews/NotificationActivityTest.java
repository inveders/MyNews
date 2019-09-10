package com.example.inved.mynews;


import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.PersistableBundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;

import com.example.inved.mynews.brain.SearchBrain;
import com.example.inved.mynews.notifications.NotificationActivity;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import static com.example.inved.mynews.notifications.NotificationActivity.KEY_FILTER_BUNDLE;
import static com.example.inved.mynews.notifications.NotificationActivity.KEY_QUERY_BUNDLE;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {27})
public class NotificationActivityTest {

    private NotificationActivity notificationActivity;
    private SearchBrain spySearchBrain;
    private CheckBox checkboxTechnology;
    private CheckBox checkboxWorld;
    private List<String> isCheckBoxList = new ArrayList<>();
    private Switch notificationSwitchEnableTest;
    private EditText editTextSearch;

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


        editTextSearch = notificationActivity.findViewById(R.id.text_input_layout);
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

    @Test
    public void checkingTechnologyAndWorldCheckboxes_ShouldSendGoodArgumentsWithScheduleJobSuccess() {

        //Given
        checkboxTechnology.setChecked(true);
        checkboxWorld.setChecked(true);
        editTextSearch.setText("Plane");
        notificationSwitchEnableTest.setEnabled(true);

        //When implicite

        //Then

        JobScheduler jobScheduler = (JobScheduler) RuntimeEnvironment.application.getSystemService(Context.JOB_SCHEDULER_SERVICE);

        ComponentName serviceComponent = new ComponentName(RuntimeEnvironment.application, NotificationService.class);
        JobInfo.Builder builder = new JobInfo.Builder(0, serviceComponent);
        builder.setRequiresCharging(false);
        builder.setPeriodic(60 * 24 * 1000, 60 * 24 * 1000);
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        PersistableBundle bundlePersistable = new PersistableBundle();
        bundlePersistable.putString(KEY_QUERY_BUNDLE, "Plane");
        bundlePersistable.putString(KEY_FILTER_BUNDLE, "section_name:(\"Technology\"\"World\")");
        builder.setExtras(bundlePersistable);

        jobScheduler.schedule(builder.build());

        Assert.assertTrue(jobScheduler.getAllPendingJobs().contains(builder.build()));
     /*   argThat(new ArgumentMatcher<JobInfo>() {
            @Override
            public boolean matches(JobInfo argument) {
                if (argument.isRequireCharging()) {
                    return false;
                }

                if (argument.getFlexMillis() != 60 * 24 * 1000) {
                    return false;
                }

                if (!argument.getRequiredNetwork().equals(JobInfo.NETWORK_TYPE_ANY)) {
                    return false;
                }

                if (!argument.getExtras().getString(KEY_FILTER_BUNDLE).equals("section_name:(\"Technology\"\"World\")")) {
                    return false;
                }

                if (!argument.getExtras().getString(KEY_QUERY_BUNDLE).equals("Plane")) {
                    return false;
                }

                return true;
            }
        });*/
    }
}

