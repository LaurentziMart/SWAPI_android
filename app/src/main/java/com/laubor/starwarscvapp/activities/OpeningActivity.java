package com.laubor.starwarscvapp.activities;


import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.laubor.starwarscvapp.R;
import com.laubor.starwarscvapp.activities.abstracts.BaseActivity;
import com.laubor.starwarscvapp.settings.Literals;
import com.laubor.starwarscvapp.settings.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OpeningActivity extends BaseActivity {
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.fabSkip)
    ExtendedFloatingActionButton fabSkip;

    MediaPlayer mPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setFullScreen();
        super.onCreate(savedInstanceState);

        config();

    }
    public int getLayoutId(){
        return R.layout.activity_splashscreen;
    }
    private void config() {
        ButterKnife.bind(this);
        fabSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchMainActivity();
            }
        });
       configMediaPlater();

        configWebView();

    }

    private void setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void configWebView() {
        webview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
               launchMainActivity();
                return true;
            }
        });
    }

    private void launchMainActivity() {

        Utils.genericAcceptCancelDialog(OpeningActivity.this, getString(R.string.skip_alert_text), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                prefs.edit().putBoolean(Literals.PREF_NO_MORE_OPENING, true).apply();
                startMainActivity();
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startMainActivity();
            }
        });

    }

    private void startMainActivity() {
        Intent intent = new Intent(OpeningActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        OpeningActivity.this.finish();
    }

    private void configMediaPlater() {
        mPlayer = MediaPlayer.create(OpeningActivity.this, R.raw.starwars_opening);
        mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

                webview.loadUrl("file:///android_asset/index.html");
                webview.setWebViewClient(new WebViewClient() {

                    public void onPageFinished(WebView view, String url) {
                        mp.start();
                        showSkipButtonDelayed();
                        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                launchMainActivity();
                            }
                        });

                    }

                });
            }
        });
    }

    private void showSkipButtonDelayed() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               fabSkip.show();
            }
        }, 10000);
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlayer.stop();
        mPlayer.release();
    }
}