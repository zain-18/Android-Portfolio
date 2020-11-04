package com.tapkrill.facebookcode.Adapter;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.tapkrill.facebookcode.Fragments.New;
import com.tapkrill.facebookcode.Model.Mainclass;
import com.tapkrill.facebookcode.R;
import com.tapkrill.facebookcode.Ui.PreviewWallapaper;

import java.util.ArrayList;

public class NewWallpaperAdpater extends RecyclerView.Adapter<NewWallpaperAdpater.ViewHolder> {

    private Context context;
    private New aNew;

    public NewWallpaperAdpater(Context context, New aNew, ArrayList<Mainclass.Photo> arrayList) {

        this.context = context;
        this.aNew = aNew;
        this.arrayList = arrayList;
    }

    private ArrayList<Mainclass.Photo> arrayList;

    @NonNull
    @Override
    public NewWallpaperAdpater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view= LayoutInflater.from(context).inflate(R.layout.custom_newwallpaper,parent,false);
       return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final NewWallpaperAdpater.ViewHolder holder, final int position) {

        Glide.with(context).load(arrayList.get(position).getSrc().getPortrait()).into(holder.imageView_new);

        holder.newWallpaperCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, PreviewWallapaper.class);
                intent.putExtra("Image",arrayList.get(position).getSrc().getPortrait());
                aNew.startActivity(intent);
                Animatoo.animateZoom(context);


            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView_new;
        CardView newWallpaperCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView_new=itemView.findViewById(R.id.newwallpaper);
            newWallpaperCard=itemView.findViewById(R.id.newWallpaperCard);

        }
    }
}
