package com.tapkrill.facebookcode.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.tapkrill.facebookcode.Fragments.Categories;
import com.tapkrill.facebookcode.Model.DataList;
import com.tapkrill.facebookcode.R;
import com.tapkrill.facebookcode.Ui.SelectedCategory;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    Context context;
    ArrayList<DataList> arrayList;
    Categories categories;

    public CategoriesAdapter(Context context, ArrayList<DataList> arrayList, Categories categories) {

        this.context = context;
        this.arrayList = arrayList;
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view= LayoutInflater.from(context).inflate(R.layout.custom_categories,parent,false);
       return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesAdapter.ViewHolder holder, final int position) {

        holder.category_iv.setImageResource(arrayList.get(position).imageId);

        holder.category_tv.setText(arrayList.get(position).txt);

        holder.categorylayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, SelectedCategory.class);
                intent.putExtra("title",arrayList.get(position).txt);
                categories.startActivity(intent);
                Animatoo.animateZoom(context);

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView category_iv;
        TextView category_tv;
        RelativeLayout categorylayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            category_iv=itemView.findViewById(R.id.category_iv);
            category_tv=itemView.findViewById(R.id.category_tv);
            categorylayout=itemView.findViewById(R.id.categorylayout);

        }
    }
}
