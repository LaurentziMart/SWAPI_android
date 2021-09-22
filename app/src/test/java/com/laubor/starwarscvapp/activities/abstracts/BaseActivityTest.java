package com.laubor.starwarscvapp.activities.abstracts;

import android.content.Intent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;

@RunWith(RobolectricTestRunner.class)
public class BaseActivityTest {

    @Test
    public void testLaunchActivity() {
        // Setup
        final Intent intent = new Intent();
        final ActivityController<BaseActivity> activityController = Robolectric.buildActivity(BaseActivity.class, intent);

        // Run the test
        final BaseActivity baseActivityUnderTest = activityController.setup().get();

        // Verify the results
    }
}
