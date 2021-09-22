package com.laubor.starwarscvapp.activities.helper;

import android.content.Context;
import android.view.ViewGroup;

import com.laubor.starwarscvapp.model.Vehicle;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class VehiclesRecyclerViewAdapterTest {

    @Mock
    private Context mockContext;

    private VehiclesRecyclerViewAdapter vehiclesRecyclerViewAdapterUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        vehiclesRecyclerViewAdapterUnderTest = new VehiclesRecyclerViewAdapter(mockContext, Arrays.asList(new Vehicle()));
    }

    @Test
    public void testOnCreateViewHolder() {
        // Setup
        final ViewGroup parent = null;

        // Run the test
        final VehiclesRecyclerViewAdapter.ViewHolder result = vehiclesRecyclerViewAdapterUnderTest.onCreateViewHolder(parent, 0);

        // Verify the results
    }

    @Test
    public void testOnBindViewHolder() {
        // Setup
        final VehiclesRecyclerViewAdapter.ViewHolder holder = null;

        // Run the test
        vehiclesRecyclerViewAdapterUnderTest.onBindViewHolder(holder, 0);

        // Verify the results
    }

    @Test
    public void testGetItemCount() {
        // Setup

        // Run the test
        final int result = vehiclesRecyclerViewAdapterUnderTest.getItemCount();

        // Verify the results
        assertEquals(0, result);
    }

    @Test
    public void testGetItem() {
        // Setup

        // Run the test
        final Vehicle result = vehiclesRecyclerViewAdapterUnderTest.getItem(0);

        // Verify the results
    }
}
