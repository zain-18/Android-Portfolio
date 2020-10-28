package com.hiddenskull.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.hiddenskull.myapplication.Fragements.DownloadedStatus;
import com.hiddenskull.myapplication.Model.DownloadModel;
import com.hiddenskull.myapplication.R;
import com.hiddenskull.myapplication.UI.VideoPlayer;

import java.util.List;

public class DownloadedAdapter extends RecyclerView.Adapter<DownloadedAdapter.ViewHolder> {
    private final List<DownloadModel> statusList;
    Context context;
    DownloadedStatus downloadedStatus;


    public DownloadedAdapter(List<DownloadModel> statusList, Context context, DownloadedStatus downloadedStatus) {
        this.statusList = statusList;
        this.context = context;
        this.downloadedStatus = downloadedStatus;
    }





    @NonNull
    @Override
    public DownloadedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.custom_downloadvideos,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DownloadedAdapter.ViewHolder holder, final int position) {

        holder.imageView.setImageBitmap(statusList.get(position).getThumbnail());

        //holder.r1_selected.setBackgroundColor(Color.parseColor("#FFFFFFF"));

        //holder.cardView.setAlpha(0);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, VideoPlayer.class);
                intent.putExtra("video",statusList.get(position).getPath());
                downloadedStatus.startActivity(intent);
            }
        });


        holder.shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareVideo("Select the App to Share video",statusList.get(position).getPath());
            }
        });
    }

    @Override
    public int getItemCount() {
        return statusList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        CardView cardView;
        Button shareBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.iv_downloadedimages);
            cardView=itemView.findViewById(R.id.r1_selected);
            shareBtn=itemView.findViewById(R.id.shareBtn);


        }
    }



    public void shareVideo(final String title, String path) {

        MediaScannerConnection.scanFile(context, new String[] { path },

                null, new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                        Intent shareIntent = new Intent(
                                android.content.Intent.ACTION_SEND);
                        shareIntent.setType("video/*");
                        shareIntent.putExtra(
                                android.content.Intent.EXTRA_SUBJECT, title);
                        shareIntent.putExtra(
                                android.content.Intent.EXTRA_TITLE, title);
                        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                        shareIntent
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                        context.startActivity(Intent.createChooser(shareIntent,title));

                    }
                });
    }


}
