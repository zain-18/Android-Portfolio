package com.tapkrill.dailymotionapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.tapkrill.dailymotionapp.Model.MainClass;
import com.tapkrill.dailymotionapp.R;

import java.util.ArrayList;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {
Context context;
    ArrayList<MainClass.List> arrayList;

    public SliderAdapter(Context context, ArrayList<MainClass.List> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    @Override
    public SliderAdapter.SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
       View view= LayoutInflater.from(context).inflate(R.layout.image_slider_layout_item,parent,false);
       return new SliderAdapterVH(view);
    }

    @Override
    public void onBindViewHolder(SliderAdapter.SliderAdapterVH viewHolder, int position) {
        Glide.with(context).load(arrayList.get(position).getThumbnail180Url()).into(viewHolder.imageView);



    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    public class SliderAdapterVH extends SliderViewAdapter.ViewHolder {
        ImageView imageView;
        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
        }
    }
}
