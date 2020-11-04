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
import com.tapkrill.dailymotionapp.Model.PlayListItems;
import com.tapkrill.dailymotionapp.R;
import com.tapkrill.dailymotionapp.UI.Player;

import java.util.ArrayList;

public class PlayListItemsAdapter extends RecyclerView.Adapter<PlayListItemsAdapter.ViewHolder> {
    Context context;
    ArrayList<PlayListItems.List> arrayList;

    public PlayListItemsAdapter(Context context, ArrayList<PlayListItems.List> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public PlayListItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.custom_playlistitems,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayListItemsAdapter.ViewHolder holder, final int position) {
        holder.playlistItem_Description.setText(arrayList.get(position).getDescription());
        Glide.with(context).load(arrayList.get(position).getThumbnail180Url()).into(holder.playlistItem_Image);
        holder.playlistItem_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, Player.class);
                intent.putExtra("video_ID",arrayList.get(position).getId());
                context.startActivity(intent);
            }
        });
        holder.playlistItem_Description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, Player.class);
                intent.putExtra("video_ID",arrayList.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void filterList(ArrayList<PlayListItems.List> filteredList){
        arrayList=filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView playlistItem_Description;
        ImageView playlistItem_Image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            playlistItem_Description=itemView.findViewById(R.id.playlistItem_Description);
            playlistItem_Image=itemView.findViewById(R.id.playlistItem_Image);

        }
    }
}
