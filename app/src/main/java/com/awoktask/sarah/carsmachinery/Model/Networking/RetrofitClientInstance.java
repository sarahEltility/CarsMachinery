package com.awoktask.sarah.carsmachinery.Model.Networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sarah on 4/6/19.
 */

public class RetrofitClientInstance {

    private static Retrofit retrofit = null;
    private static final String Base_URL = "http://api.emiratesauction.com/v2/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit==null){

            retrofit = new Retrofit.Builder()
            .baseUrl(Base_URL)
            .addConverterFactory(GsonConverterFactory.create())
             .build();
        }
        return retrofit;
    }


}
