package com.laubor.starwarscvapp.activities.helper;

import android.content.Context;
import android.view.ViewGroup;

import com.laubor.starwarscvapp.model.Character;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class SWAPIRecyclerAdapterTest {

    @Mock
    private Context mockContext;

    private SWAPIRecyclerAdapter swapiRecyclerAdapterUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        swapiRecyclerAdapterUnderTest = new SWAPIRecyclerAdapter(Arrays.asList(new Character()), mockContext, null);
    }

    @Test
    public void testOnCreateViewHolder() {
        // Setup
        final ViewGroup parent = null;

        // Run the test
        final BaseViewHolder result = swapiRecyclerAdapterUnderTest.onCreateViewHolder(parent, 0);

        // Verify the results
    }

    @Test
    public void testOnBindViewHolder() {
        // Setup
        final BaseViewHolder holder = null;

        // Run the test
        swapiRecyclerAdapterUnderTest.onBindViewHolder(holder, 0);

        // Verify the results
    }

    @Test
    public void testGetItemViewType() {
        // Setup

        // Run the test
        final int result = swapiRecyclerAdapterUnderTest.getItemViewType(0);

        // Verify the results
        assertEquals(0, result);
    }

    @Test
    public void testGetItemCount() {
        // Setup

        // Run the test
        final int result = swapiRecyclerAdapterUnderTest.getItemCount();

        // Verify the results
        assertEquals(0, result);
    }

    @Test
    public void testAddItems() {
        // Setup
        final List<Character> characters = Arrays.asList(new Character());

        // Run the test
        swapiRecyclerAdapterUnderTest.addItems(characters);

        // Verify the results
    }

    @Test
    public void testSetItems() {
        // Setup
        final List<Character> characters = Arrays.asList(new Character());

        // Run the test
        swapiRecyclerAdapterUnderTest.setItems(characters);

        // Verify the results
    }

    @Test
    public void testAddLoading() {
        // Setup

        // Run the test
        swapiRecyclerAdapterUnderTest.addLoading();

        // Verify the results
    }

    @Test
    public void testRemoveLoading() {
        // Setup

        // Run the test
        swapiRecyclerAdapterUnderTest.removeLoading();

        // Verify the results
    }

    @Test
    public void testClear() {
        // Setup

        // Run the test
        swapiRecyclerAdapterUnderTest.clear();

        // Verify the results
    }

    @Test
    public void testGetItem() {
        // Setup

        // Run the test
        final Character result = swapiRecyclerAdapterUnderTest.getItem(0);

        // Verify the results
    }

    @Test
    public void testSortCurrentItemsByType() {
        // Setup

        // Run the test
        swapiRecyclerAdapterUnderTest.sortCurrentItemsByType(SWAPIRecyclerAdapter.SortType.ALPHABETICALLY);

        // Verify the results
    }

    @Test
    public void testSetClickListener() {
        // Setup
        final SWAPIRecyclerAdapter.ItemClickListener itemClickListener = null;

        // Run the test
        swapiRecyclerAdapterUnderTest.setClickListener(itemClickListener);

        // Verify the results
    }
}
