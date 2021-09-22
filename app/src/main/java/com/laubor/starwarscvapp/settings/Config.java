package com.laubor.starwarscvapp.settings;

import com.laubor.starwarscvapp.BuildConfig;

/**
 * Created by laurentzi
 */
public class Config {
    public static String ENDPOINT;
    public static boolean checkQrClient=true;
    public static boolean checkQrDate=true;
    public static boolean checkQrCheckInWithoutCheckoutDifferentDay=true;

    public static String getENDPOINT(){
        if (BuildConfig.DEBUG ) {
            ENDPOINT="https://swapi.dev/api/" ;


        }else{
            ENDPOINT="https://swapi.dev/api/" ;

        }
            return ENDPOINT;
    }


    public static void setENDPOINT(String endpoint){
        ENDPOINT=endpoint;
    }

}
