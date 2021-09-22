package com.laubor.starwarscvapp.activities;

import android.content.Intent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;

@RunWith(RobolectricTestRunner.class)
public class SplashScreenActivityTest {

    @Test
    public void testLaunchActivity() {
        // Setup
        final Intent intent = new Intent();
        final ActivityController<SplashScreenActivity> activityController = Robolectric.buildActivity(SplashScreenActivity.class, intent);

        // Run the test
        final SplashScreenActivity splashScreenActivityUnderTest = activityController.setup().get();

        // Verify the results
    }
}
