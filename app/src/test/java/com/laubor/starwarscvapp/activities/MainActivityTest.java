package com.laubor.starwarscvapp.activities;

import android.content.Intent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    @Test
    public void testLaunchActivity() {
        // Setup
        final Intent intent = new Intent();
        final ActivityController<MainActivity> activityController = Robolectric.buildActivity(MainActivity.class, intent);

        // Run the test
        final MainActivity mainActivityUnderTest = activityController.setup().get();

        // Verify the results
    }
}
