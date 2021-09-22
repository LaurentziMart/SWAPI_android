package com.laubor.starwarscvapp.controller;

import java.io.IOException;

import retrofit2.Response;


public class Controller {

    static Throwable generateThrowableMessage(Response response)  {
        String message=response.message();

        try {
            message+=response.errorBody()!=null?": "+response.errorBody().string():"";
        } catch (IOException e) {
            return new Throwable(response.message());
        }

        return new Throwable(message);
    }
}
