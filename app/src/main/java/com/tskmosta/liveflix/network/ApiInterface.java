package com.tskmosta.liveflix.network;

import com.tskmosta.liveflix.model.Channels;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("channels")
    Call<List<Channels>> getAllChannells();
}
