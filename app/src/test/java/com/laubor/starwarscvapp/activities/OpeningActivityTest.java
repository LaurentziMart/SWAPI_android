package com.laubor.starwarscvapp.activities;

import android.content.Intent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;

@RunWith(RobolectricTestRunner.class)
public class OpeningActivityTest {

    @Test
    public void testLaunchActivity() {
        // Setup
        final Intent intent = new Intent();
        final ActivityController<OpeningActivity> activityController = Robolectric.buildActivity(OpeningActivity.class, intent);

        // Run the test
        final OpeningActivity openingActivityUnderTest = activityController.setup().get();

        // Verify the results
    }
}
