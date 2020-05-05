package com.tskmosta.liveflix.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.tskmosta.liveflix.model.Channels;
import com.tskmosta.liveflix.repository.ChannelsRepository;

public class ChannelsViewModel extends AndroidViewModel {

    ChannelsRepository channelsRepository;

    public ChannelsViewModel(@NonNull Application application) {
        super(application);

        channelsRepository = new ChannelsRepository();
    }

    public LiveData<Channels> liveData(){
        return channelsRepository.getMutableLiveData();
    }
}
