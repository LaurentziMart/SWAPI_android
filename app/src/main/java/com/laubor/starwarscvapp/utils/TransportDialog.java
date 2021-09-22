package com.laubor.starwarscvapp.utils;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laubor.starwarscvapp.R;
import com.laubor.starwarscvapp.model.Starship;
import com.laubor.starwarscvapp.model.Transport;
import com.laubor.starwarscvapp.model.Vehicle;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransportDialog {
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.edModel)
    EditText edModel;
    @BindView(R.id.edManufacturer)
    EditText edManufacturer;
    @BindView(R.id.edCredits)
    EditText edCredits;
    @BindView(R.id.edLength)
    EditText edLength;
    @BindView(R.id.edMaxSpeed)
    EditText edMaxSpeed;
    @BindView(R.id.edCrew)
    EditText edCrew;
    @BindView(R.id.edPassengers)
    EditText edPassengers;
    @BindView(R.id.edCargoCapacity)
    EditText edCargoCapacity;
    @BindView(R.id.edConsumables)
    EditText edConsumables;
    @BindView(R.id.edVehicleClass)
    EditText edVehicleClass;
    @BindView(R.id.edMGLT)
    EditText edMGLT;
    @BindView(R.id.edHyperdriveRating)
    EditText edHyperdriveRating;
     @BindView(R.id.llStarship)
    LinearLayout llStarship;
    @BindView(R.id.btn_dialog)
     Button dialogButton;

    public void showDialog(Activity activity, Transport transport){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.transport_info_dialog);
        ButterKnife.bind(this,dialog);

        tvName.setText(activity.getString(R.string.transport_name,transport.getName()));
        edModel.setText(transport.getModel());
        edManufacturer.setText(transport.getManufacturer());
        edCredits.setText(transport.getCost_in_credits());
        edLength.setText(transport.getLength());
        edMaxSpeed.setText(transport.getMax_atmosphering_speed());
        edCrew.setText(transport.getCrew());
        edPassengers.setText(transport.getPassengers());
        edConsumables.setText(transport.getConsumables());

        if(transport instanceof Vehicle){
            llStarship.setVisibility(View.GONE);
            edVehicleClass.setText(((Vehicle)transport).getVehicle_class());
        }else{
            edVehicleClass.setText(((Starship)transport).getStarship_class());
            edMGLT.setText(((Starship)transport).getMGLT());
            edHyperdriveRating.setText(((Starship)transport).getHyperdrive_rating());
        }


        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }
}