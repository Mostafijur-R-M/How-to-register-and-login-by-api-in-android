package com.tskmosta.liveflix.network;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    static Retrofit retrofit;
    final static String BASE_URL = "http://mostafij.thesoftking.com/laravelapi/blog/public/";

    public static Retrofit getApiClient(){

        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
