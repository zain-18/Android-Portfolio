package com.hiddenskull.zain_.youtubeplayer.UI;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hiddenskull.zain_.youtubeplayer.Model.Item;
import com.hiddenskull.zain_.youtubeplayer.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
public  String videoId;
    private ArrayList<Item> items;

    public DataAdapter(ArrayList<Item> items) {
        this.items = items;
    }

    private Context context;

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_youtubeplaylist, viewGroup, false);
        return new DataAdapter.ViewHolder(view);

    }


    public void onBindViewHolder(ViewHolder viewHolder, final int i) {

        viewHolder.titletv.setText(items.get(i).getSnippet().getTitle());
        String url = items.get(i).getSnippet().getThumbnails().getHigh().getUrl();
        Picasso.get().load(url).into(viewHolder.imgtv);


viewHolder.card1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent intent=new Intent(v.getContext(),MainActivity.class);
        intent.putExtra("id",videoId=items.get(i).getSnippet().getResourceId().getVideoId());
        v.getContext().startActivity(intent);

    }
});
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titletv, description;
        ImageView imgtv;
CardView card1;
        public ViewHolder(@NonNull View view) {
            super(view);
            titletv = view.findViewById(R.id.title_id);
            imgtv = view.findViewById(R.id.imageview);
           card1 = view.findViewById(R.id.card1);

        }


    }

}
