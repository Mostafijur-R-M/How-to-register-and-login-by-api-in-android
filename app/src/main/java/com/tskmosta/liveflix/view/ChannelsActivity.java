package com.tskmosta.liveflix.view;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.tskmosta.liveflix.R;
import com.tskmosta.liveflix.adapter.ChannelsAdapter;
import com.tskmosta.liveflix.model.Channels;
import com.tskmosta.liveflix.network.ApiClient;
import com.tskmosta.liveflix.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChannelsActivity extends AppCompatActivity {

    RecyclerView.LayoutManager layoutManager;
    ChannelsAdapter channelsAdapter;
    RecyclerView recyclerView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channels);

       showDialogMethod();

        recyclerView = findViewById(R.id.channels_list_rv_id);

        netWorkCall();
    }

    private void netWorkCall() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<Channels>> call = apiInterface.getAllChannells();

        call.enqueue(new Callback<List<Channels>>() {
            @Override
            public void onResponse(Call<List<Channels>> call, Response<List<Channels>> response) {
                progressDialog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Channels>> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("asdf", t.getLocalizedMessage());
                Toast.makeText(ChannelsActivity.this, "ERROR: "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateDataList(List<Channels> channelsList) {
        channelsAdapter = new ChannelsAdapter(ChannelsActivity.this, channelsList);

        layoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(channelsAdapter);
    }

    private void showDialogMethod() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading....");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }
}
