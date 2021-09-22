package com.laubor.starwarscvapp;

import android.app.Application;

import com.laubor.starwarscvapp.module.AppModule;

public class SWAPIApplication extends Application {
    private SWAPIApplication instance;
    private SWAPIcomponent component;
    public void onCreate() {

        super.onCreate();

        instance = this;
        component = DaggerSWAPIcomponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public SWAPIcomponent getComponent() {
        return component;
    }

    public SWAPIApplication getInstance() {
        return instance;
    }
}
