package com.laubor.starwarscvapp.activities.helper;

import android.content.Context;
import android.view.ViewGroup;

import com.laubor.starwarscvapp.model.Starship;
import com.laubor.starwarscvapp.model.Transport;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class StarshipsRecyclerViewAdapterTest {

    @Mock
    private Context mockContext;

    private StarshipsRecyclerViewAdapter starshipsRecyclerViewAdapterUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        starshipsRecyclerViewAdapterUnderTest = new StarshipsRecyclerViewAdapter(mockContext, Arrays.asList(new Starship()));
    }

    @Test
    public void testOnCreateViewHolder() {
        // Setup
        final ViewGroup parent = null;

        // Run the test
        final StarshipsRecyclerViewAdapter.ViewHolder result = starshipsRecyclerViewAdapterUnderTest.onCreateViewHolder(parent, 0);

        // Verify the results
    }

    @Test
    public void testOnBindViewHolder() {
        // Setup
        final StarshipsRecyclerViewAdapter.ViewHolder holder = null;

        // Run the test
        starshipsRecyclerViewAdapterUnderTest.onBindViewHolder(holder, 0);

        // Verify the results
    }

    @Test
    public void testGetItemCount() {
        // Setup

        // Run the test
        final int result = starshipsRecyclerViewAdapterUnderTest.getItemCount();

        // Verify the results
        assertEquals(0, result);
    }

    @Test
    public void testGetItem() {
        // Setup

        // Run the test
        final Transport result = starshipsRecyclerViewAdapterUnderTest.getItem(0);

        // Verify the results
    }
}
