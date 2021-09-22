package com.laubor.starwarscvapp.settings;

import android.content.Context;

import com.chuckerteam.chucker.api.ChuckerInterceptor;
import com.laubor.starwarscvapp.BuildConfig;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;


import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Authentication class
 * here we will config certificate based auth, or oauth access
 * Created by laurentzi
 */

public class Authentication {
    private String baseAPIURL;

    private Certificate certificate;

    /**
     * Authentication object for config REST API connection,
     * @param baseAPIURL
     *
     */
    public Authentication(String baseAPIURL) {
        this.baseAPIURL = baseAPIURL;

    }

    public String getBaseAPIURL() {
        return baseAPIURL;
    }

    public  OkHttpClient getClient(final Context context) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {
        OkHttpClient.Builder okHttpBuilder= new OkHttpClient.Builder();
        okHttpBuilder.connectTimeout(300, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS)

                .writeTimeout(300, TimeUnit.SECONDS);
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        HttpLoggingInterceptor loggingHeaders = new HttpLoggingInterceptor();
        loggingHeaders.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        ChuckerInterceptor chucker =new ChuckerInterceptor(context);

        if (BuildConfig.DEBUG) {

            okHttpBuilder.addInterceptor(loggingHeaders);  // To see requests headers
            okHttpBuilder.addInterceptor(logging);  // to see requests json
            okHttpBuilder.addInterceptor(chucker);  // to see requests with chucker debug interface

        }
        okHttpBuilder .addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request.Builder ongoing = chain.request().newBuilder();
               //intercept requests data and react when problems
                return chain.proceed(ongoing.build());
            }
        });

        return okHttpBuilder.build();
    }



    public static Authentication getAuthentication() {
       Authentication auth= new Authentication(Config.getENDPOINT());

        return auth;
    }




}
