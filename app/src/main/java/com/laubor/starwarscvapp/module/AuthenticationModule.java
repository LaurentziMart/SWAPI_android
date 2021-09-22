package com.laubor.starwarscvapp.module;



import com.laubor.starwarscvapp.R;
import com.laubor.starwarscvapp.SWAPIApplication;
import com.laubor.starwarscvapp.settings.Authentication;
import com.laubor.starwarscvapp.settings.Config;

import java.io.IOException;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class AuthenticationModule {
    @Provides
    @Singleton
    Authentication providesAuthentication() {
        Authentication auth= new Authentication(Config.getENDPOINT());

        return auth;
    }

}
