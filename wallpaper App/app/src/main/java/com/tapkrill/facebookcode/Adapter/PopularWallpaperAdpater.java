package com.tapkrill.facebookcode.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.tapkrill.facebookcode.Fragments.Popular;
import com.tapkrill.facebookcode.Model.Mainclass;
import com.tapkrill.facebookcode.R;
import com.tapkrill.facebookcode.Ui.PreviewWallapaper;

import java.util.ArrayList;

public class PopularWallpaperAdpater extends RecyclerView.Adapter<PopularWallpaperAdpater.ViewHolder> {

    Context context;
    ArrayList<Mainclass.Photo>popularArrayList;
    Popular popular;

    public PopularWallpaperAdpater(Context context, ArrayList<Mainclass.Photo> popularArrayList, Popular popular) {

        this.context = context;
        this.popularArrayList = popularArrayList;
        this.popular = popular;

    }

    @NonNull
    @Override
    public PopularWallpaperAdpater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.custom_popularwallpaper,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularWallpaperAdpater.ViewHolder holder, final int position) {

        Glide.with(context).load(popularArrayList.get(position).getSrc().getPortrait()).into(holder.popular_imgview);

        holder.popularwallpaprecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, PreviewWallapaper.class);
                intent.putExtra("Image",popularArrayList.get(position).getSrc().getPortrait());
                popular.startActivity(intent);
                Animatoo.animateZoom(context);
            }
        });

    }

    @Override
    public int getItemCount() {
        return popularArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView popular_imgview;
        CardView popularwallpaprecard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            popular_imgview=itemView.findViewById(R.id.popularwallpaper);
            popularwallpaprecard=itemView.findViewById(R.id.popularwallpaprecard);

        }
    }
}
