package com.laubor.starwarscvapp.activities;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.laubor.starwarscvapp.R;
import com.laubor.starwarscvapp.activities.abstracts.BaseActivity;
import com.laubor.starwarscvapp.settings.Literals;

public class SplashScreenActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setFullScreen();
        super.onCreate(savedInstanceState);

        startApp();
        

    }



    private void setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void startApp() {
        if(prefs.getBoolean(Literals.PREF_NO_MORE_OPENING,false)){
            startMainActivity();
        }else{
            startDelayedOpening();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash_screen;
    }

    private void startMainActivity() {
        Intent in = new Intent(SplashScreenActivity.this,MainActivity.class);
        startActivity(in);

        finish();
    }

    private void startDelayedOpening() {
        new Handler().postDelayed(new Runnable() {
            public void run() {

                Intent in = new Intent(SplashScreenActivity.this,OpeningActivity.class);
                startActivity(in);

                finish();
            }
        }, 3000);
    }
}