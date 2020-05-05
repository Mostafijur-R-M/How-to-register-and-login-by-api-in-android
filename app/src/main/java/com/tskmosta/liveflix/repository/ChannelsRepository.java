package com.tskmosta.liveflix.repository;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.tskmosta.liveflix.model.Channels;
import com.tskmosta.liveflix.network.ApiClient;
import com.tskmosta.liveflix.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChannelsRepository {

    MutableLiveData<Channels> mutableLiveData;
    Channels channelsList;

    public MutableLiveData<Channels> getMutableLiveData(){
        if (mutableLiveData == null){
            mutableLiveData = new MutableLiveData<>();
        }

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Channels> call = apiInterface.getAllChannells();

        call.enqueue(new Callback<Channels>() {
            @Override
            public void onResponse(Call<Channels> call, Response<Channels> response) {
                channelsList = response.body();
                mutableLiveData.setValue(channelsList);
            }

            @Override
            public void onFailure(Call<Channels> call, Throwable t) {
                Log.e("asdf", t.getLocalizedMessage());
            }
        });

        return mutableLiveData;
    }
}
