package com.laubor.starwarscvapp.utils;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laubor.starwarscvapp.model.Transport;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
public class TransportDialogTest {

    private TransportDialog transportDialogUnderTest;

    @Before
    public void setUp() {
        transportDialogUnderTest = new TransportDialog();
        transportDialogUnderTest.tvName = mock(TextView.class);
        transportDialogUnderTest.edModel = mock(EditText.class);
        transportDialogUnderTest.edManufacturer = mock(EditText.class);
        transportDialogUnderTest.edCredits = mock(EditText.class);
        transportDialogUnderTest.edLength = mock(EditText.class);
        transportDialogUnderTest.edMaxSpeed = mock(EditText.class);
        transportDialogUnderTest.edCrew = mock(EditText.class);
        transportDialogUnderTest.edPassengers = mock(EditText.class);
        transportDialogUnderTest.edCargoCapacity = mock(EditText.class);
        transportDialogUnderTest.edConsumables = mock(EditText.class);
        transportDialogUnderTest.edVehicleClass = mock(EditText.class);
        transportDialogUnderTest.edMGLT = mock(EditText.class);
        transportDialogUnderTest.edHyperdriveRating = mock(EditText.class);
        transportDialogUnderTest.llStarship = mock(LinearLayout.class);
        transportDialogUnderTest.dialogButton = mock(Button.class);
    }

    @Test
    public void testShowDialog() {
        // Setup
        final Activity activity = new Activity();
        final Transport transport = new Transport();
        transport.setCargo_capacity("cargo_capacity");
        transport.setConsumables("consumables");
        transport.setCost_in_credits("cost_in_credits");
        transport.setCreated("created");
        transport.setCrew("crew");
        transport.setEdited("edited");
        transport.setLength("length");
        transport.setManufacturer("manufacturer");
        transport.setMax_atmosphering_speed("max_atmosphering_speed");
        transport.setModel("model");

        // Run the test
        transportDialogUnderTest.showDialog(activity, transport);

        // Verify the results
        verify(transportDialogUnderTest.tvName).setText("text");
        verify(transportDialogUnderTest.edModel).setText("text");
        verify(transportDialogUnderTest.edManufacturer).setText("text");
        verify(transportDialogUnderTest.edCredits).setText("text");
        verify(transportDialogUnderTest.edLength).setText("text");
        verify(transportDialogUnderTest.edMaxSpeed).setText("text");
        verify(transportDialogUnderTest.edCrew).setText("text");
        verify(transportDialogUnderTest.edPassengers).setText("text");
        verify(transportDialogUnderTest.edConsumables).setText("text");
        verify(transportDialogUnderTest.llStarship).setVisibility(0);
        verify(transportDialogUnderTest.edVehicleClass).setText("text");
        verify(transportDialogUnderTest.edMGLT).setText("text");
        verify(transportDialogUnderTest.edHyperdriveRating).setText("text");
        verify(transportDialogUnderTest.dialogButton).setOnClickListener(any(View.OnClickListener.class));
    }
}
