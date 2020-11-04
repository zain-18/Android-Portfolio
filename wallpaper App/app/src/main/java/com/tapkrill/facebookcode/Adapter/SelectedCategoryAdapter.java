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
import com.tapkrill.facebookcode.Model.Mainclass;
import com.tapkrill.facebookcode.R;
import com.tapkrill.facebookcode.Ui.PreviewWallapaper;

import java.util.ArrayList;

public class SelectedCategoryAdapter extends RecyclerView.Adapter<SelectedCategoryAdapter.ViewHolder> {
    Context context;
    ArrayList<Mainclass.Photo> categoryarrayList;

    public SelectedCategoryAdapter(Context context, ArrayList<Mainclass.Photo> categoryarrayList) {

        this.context = context;
        this.categoryarrayList = categoryarrayList;
    }

    @NonNull
    @Override
    public SelectedCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view= LayoutInflater.from(context).inflate(R.layout.custom_selectedcategory,parent,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectedCategoryAdapter.ViewHolder holder, final int position) {

        Glide.with(context).load(categoryarrayList.get(position).getSrc().getPortrait()).into(holder.categorywallpaper);

        holder.selectedwallpaprecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context, PreviewWallapaper.class);
                intent.putExtra("Image",categoryarrayList.get(position).getSrc().getPortrait());
                context.startActivity(intent);
                Animatoo.animateZoom(context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryarrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView categorywallpaper;
        CardView selectedwallpaprecard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            categorywallpaper=itemView.findViewById(R.id.categorywallpaper);
            selectedwallpaprecard=itemView.findViewById(R.id.selectedwallpaprecard);
        }
    }
}
