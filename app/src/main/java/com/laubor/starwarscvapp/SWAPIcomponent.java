package com.laubor.starwarscvapp;



import com.laubor.starwarscvapp.activities.abstracts.BaseActivity;
import com.laubor.starwarscvapp.module.AppModule;
import com.laubor.starwarscvapp.module.AuthenticationModule;
import com.laubor.starwarscvapp.module.RestClientModule;

import javax.inject.Singleton;

import dagger.Component;


    @Singleton
    @Component(modules = {AppModule.class, RestClientModule.class,AuthenticationModule.class})
    public interface SWAPIcomponent {
        void inject(BaseActivity baseActivity);

    }






