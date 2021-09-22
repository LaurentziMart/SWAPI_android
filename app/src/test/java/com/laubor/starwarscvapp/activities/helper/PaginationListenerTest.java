package com.laubor.starwarscvapp.activities.helper;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class PaginationListenerTest {

    @Mock
    private LinearLayoutManager mockLayoutManager;

    private PaginationListener paginationListenerUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        paginationListenerUnderTest = new PaginationListener(mockLayoutManager) {
            @Override
            protected void loadMoreItems() {

            }

            @Override
            public boolean isLastPage() {
                return false;
            }

            @Override
            public boolean isLoading() {
                return false;
            }
        };
    }

    @Test
    public void testOnScrolled() {
        // Setup
        final RecyclerView recyclerView = new RecyclerView(null);
        when(mockLayoutManager.getChildCount()).thenReturn(0);
        when(mockLayoutManager.getItemCount()).thenReturn(0);
        when(mockLayoutManager.findFirstVisibleItemPosition()).thenReturn(0);

        // Run the test
        paginationListenerUnderTest.onScrolled(recyclerView, 0, 0);

        // Verify the results
    }
}
