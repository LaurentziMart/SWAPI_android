package com.laubor.starwarscvapp;

import android.content.Context;

import com.laubor.starwarscvapp.controller.SWAPIController;
import com.laubor.starwarscvapp.settings.Authentication;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class RestClientImplTest {

    @Mock
    private Authentication mockAuth;
    @Mock
    private Context mockContext;

    private RestClientImpl restClientImplUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        restClientImplUnderTest = new RestClientImpl(mockAuth, mockContext);
    }

    @Test
    public void testGetCharacter() {
        // Setup
        final SWAPIController.CharacterCallback callback = null;

        // Run the test
        restClientImplUnderTest.getCharacter("characterUrl", callback);

        // Verify the results
    }

    @Test
    public void testGetPeople() {
        // Setup
        final SWAPIController.PeopleCallback callback = null;

        // Run the test
        restClientImplUnderTest.getPeople(0, callback);

        // Verify the results
    }

    @Test
    public void testSearchPeople() {
        // Setup
        final SWAPIController.PeopleCallback callback = null;

        // Run the test
        restClientImplUnderTest.searchPeople("searchQuery", 0, callback);

        // Verify the results
    }
}
