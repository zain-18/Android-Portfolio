package com.tapkrill.dailymotionapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tapkrill.dailymotionapp.Model.MainClass;
import com.tapkrill.dailymotionapp.R;
import com.tapkrill.dailymotionapp.UI.PlayListItems;

import java.util.ArrayList;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {

    ArrayList<MainClass.List> arrayList;
    Context context;

    public PlaylistAdapter(ArrayList<MainClass.List> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public PlaylistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.custom_playlistview,parent,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistAdapter.ViewHolder holder, final int position) {

            holder.playlist_Name.setText(arrayList.get(position).getName());
           Glide.with(context).load(arrayList.get(position).getThumbnail180Url()).into(holder.playlist_Image);
           holder.playlist_Image.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent=new Intent(context, PlayListItems.class);
                   intent.putExtra("playlistid",arrayList.get(position).getId());
                   context.startActivity(intent);
               }
           });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView playlist_Name;
        ImageView playlist_Image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            playlist_Name=itemView.findViewById(R.id.playlist_Name);
            playlist_Image=itemView.findViewById(R.id.playlist_Image);
        }
    }
}
