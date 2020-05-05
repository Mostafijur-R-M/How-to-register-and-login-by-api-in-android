package com.tskmosta.liveflix.view;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import com.tskmosta.liveflix.R;
import com.tskmosta.liveflix.adapter.ChannelsAdapter;
import com.tskmosta.liveflix.model.Channels;
import com.tskmosta.liveflix.viewmodel.ChannelsViewModel;

import java.util.ArrayList;
import java.util.List;

public class ChannelsActivity extends AppCompatActivity {

    ChannelsViewModel channelsViewModel;
    List<Channels> channelsList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    ChannelsAdapter channelsAdapter;
    RecyclerView recyclerView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channels);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading....");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        recyclerView = findViewById(R.id.channels_list_rv_id);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        netWorkCall();
    }

    private void netWorkCall() {
        channelsViewModel = ViewModelProviders.of(this).get(ChannelsViewModel.class);
        channelsViewModel.liveData().observe(this, new Observer<Channels>() {
            @Override
            public void onChanged(Channels channels) {
                //channelsList = channels.

                progressDialog.dismiss();

                channelsAdapter = new ChannelsAdapter(ChannelsActivity.this, channelsList);
                recyclerView.setAdapter(channelsAdapter);
                recyclerView.setLayoutManager(layoutManager);
            }
        });
    }
}
