package com.laubor.starwarscvapp.module;

import android.content.Context;

import com.laubor.starwarscvapp.RestClient;
import com.laubor.starwarscvapp.RestClientImpl;
import com.laubor.starwarscvapp.settings.Authentication;


import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Created by laurentzi
 */
@Module
public class RestClientModule {



    @Provides
    @Singleton
    RestClient provideVigilantesClientImpl(Authentication auth, Context context) {
            return new RestClientImpl(auth,context);

    }


}
