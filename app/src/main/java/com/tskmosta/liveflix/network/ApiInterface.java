package com.tskmosta.liveflix.network;

import com.tskmosta.liveflix.model.Channels;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("channels")
    Call<Channels> getAllChannells();
}
