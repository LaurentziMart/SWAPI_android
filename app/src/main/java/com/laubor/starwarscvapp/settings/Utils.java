package com.laubor.starwarscvapp.settings;


import android.app.Activity;
import android.content.DialogInterface;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.laubor.starwarscvapp.R;

/**
 * Created by laurentzi-martinez on 07/11/2017.
 */

public class Utils {




    public static void genericAcceptCancelDialog(Activity context, String message, DialogInterface.OnClickListener acceptCallback, DialogInterface.OnClickListener cancelCallback) {

        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, R.style.Theme_AppCompat_Light_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        try {
            builder .setMessage(message)
                    .setPositiveButton(android.R.string.yes, acceptCallback)
                    .setNegativeButton(android.R.string.no,cancelCallback)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        } catch (Exception e) {
           
        }
    }




}

