package com.hiddenskull.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.hiddenskull.myapplication.Model.VideosModel;
import com.hiddenskull.myapplication.R;
import com.hiddenskull.myapplication.UI.VideoPlayer;

import java.util.ArrayList;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.ViewHolder> {
    ArrayList<VideosModel> VideosModelListArrayList;
    Context context;

    public VideosAdapter(ArrayList<VideosModel> videosModelListArrayList, Context context) {
        VideosModelListArrayList = videosModelListArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public VideosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.custom_videos,parent,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideosAdapter.ViewHolder holder, final int position) {
        holder.imageView.setImageBitmap(VideosModelListArrayList.get(position).getThumbnail());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, VideoPlayer.class);
                intent.putExtra("video",VideosModelListArrayList.get(position).getPath());
                intent.putExtra("title",VideosModelListArrayList.get(position).getTitle());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return VideosModelListArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.iv_videos);
            cardView=itemView.findViewById(R.id.videoscardview);
        }
    }
}
