package com.laubor.starwarscvapp.activities.abstracts;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

import com.laubor.starwarscvapp.RestClient;
import com.laubor.starwarscvapp.SWAPIApplication;

import javax.inject.Inject;

import safety.com.br.android_shake_detector.core.ShakeCallback;
import safety.com.br.android_shake_detector.core.ShakeDetector;
import safety.com.br.android_shake_detector.core.ShakeOptions;
import butterknife.BindView;
import butterknife.ButterKnife;
public abstract class BaseActivity extends AppCompatActivity {

    protected SharedPreferences prefs;
    protected boolean firstTimeShaking=true;
    private ShakeDetector shakeDetector;
    @Inject
    public
    RestClient client;

    @Inject
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        configBase();


    }

    private void configBase() {
        ((SWAPIApplication)getApplication()).getComponent().inject(this);
        prefs= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ButterKnife.bind(this);
    }

    protected void initShakeConfig(ShakeCallback callback) {

        ShakeOptions options = new ShakeOptions()
                .background(true)
                .interval(500)
                .shakeCount(1)
                .sensibility(2.3f);

        this.shakeDetector = new ShakeDetector(options).start(this,callback);
    }


    public abstract int getLayoutId();


    public enum SaberType{
        OPENING,CLOSING,PLAYING
    }

    @Override
    protected void onDestroy() {
        if(shakeDetector!=null)shakeDetector.destroy(getBaseContext());
        super.onDestroy();
    }
}