package com.hiddenskull.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hiddenskull.myapplication.Model.FolderName;
import com.hiddenskull.myapplication.R;
import com.hiddenskull.myapplication.UI.Videos;

import java.util.ArrayList;

public class FolderNameAdapter extends RecyclerView.Adapter<FolderNameAdapter.ViewHolder> {

    ArrayList<FolderName> arrayList;
    Context context;

    public FolderNameAdapter(ArrayList<FolderName> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public FolderNameAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.custom_foldername,parent,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FolderNameAdapter.ViewHolder holder, final int position) {


        holder.foldername.setText(arrayList.get(position).getName());

        holder.folder_Imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, Videos.class);
                intent.putExtra("pathname",arrayList.get(position).getPath());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {

        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView foldername;
        ImageView folder_Imageview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            foldername=itemView.findViewById(R.id.foldername);
            folder_Imageview=itemView.findViewById(R.id.folder_Imageview);
        }
    }
}
