package com.tskmosta.liveflix.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tskmosta.liveflix.R;
import com.tskmosta.liveflix.model.Channels;
import com.tskmosta.liveflix.view.ChannelsPLayerActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChannelsAdapter extends RecyclerView.Adapter<ChannelsAdapter.MyViewHolder> {

    private Context context;
    List<Channels> channelsList;

    public ChannelsAdapter(Context context, List<Channels> channelsList) {
        this.context = context;
        this.channelsList = channelsList;
    }

    @NonNull
    @Override
    public ChannelsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.channels_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ChannelsAdapter.MyViewHolder holder, final int position) {

        holder.channelsNameTV.setText(channelsList.get(position).getName());
        Glide.with(context).load(channelsList.get(position).getImage()).into(holder.channelImageCV);

        final String videoUrl = channelsList.get(position).getVideoUrl();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ChannelsPLayerActivity.class);
                intent.putExtra("videoUrl", videoUrl);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return channelsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView channelsNameTV;
        CircleImageView channelImageCV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            channelsNameTV = itemView.findViewById(R.id.channels_name_tv_id);
            channelImageCV = itemView.findViewById(R.id.channels_image_cv_id);
        }
    }
}
