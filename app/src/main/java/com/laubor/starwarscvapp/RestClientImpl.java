package com.laubor.starwarscvapp;

import android.content.Context;

import com.laubor.starwarscvapp.controller.SWAPIController;
import com.laubor.starwarscvapp.settings.Authentication;
import com.laubor.starwarscvapp.settings.RetrofitFactory;


import retrofit2.Retrofit;

/**
 * Created by laurentzi
 */

public class RestClientImpl implements RestClient {

    private Authentication auth;
    private Retrofit retrofit;
    private static RestClient client;
    private Context context;


    public RestClientImpl(final Authentication auth, Context context)  {
        this.auth = auth;
        this.context=context;
        retrofit = RetrofitFactory.getRetrofit(context);

    }


    @Override
    public void getCharacter(String characterUrl, SWAPIController.CharacterCallback callback) {
        SWAPIController.getCharacter(retrofit,characterUrl,callback);
    }

    @Override
    public void getPeople(int pagination, SWAPIController.PeopleCallback callback) {
        SWAPIController.getPeople(retrofit,pagination, callback);
    }

    @Override
    public void searchPeople(String searchQuery, int pagination, SWAPIController.PeopleCallback callback) {
        SWAPIController.searchPeople(retrofit,pagination,searchQuery,callback);
    }
}
