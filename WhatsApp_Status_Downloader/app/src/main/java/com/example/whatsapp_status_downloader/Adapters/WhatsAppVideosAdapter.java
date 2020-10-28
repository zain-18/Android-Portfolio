package com.example.whatsapp_status_downloader.Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsapp_status_downloader.Fragments.WhatsAppVideos;
import com.example.whatsapp_status_downloader.Model.StatusModel;
import com.example.whatsapp_status_downloader.R;
import com.example.whatsapp_status_downloader.UI.PreviewVideo;
import com.example.whatsapp_status_downloader.UI.ViewImage;

import java.io.File;
import java.util.List;

public class WhatsAppVideosAdapter extends RecyclerView.Adapter<WhatsAppVideosAdapter.ViewHolder> {
    private final List<StatusModel>StatusModelList;
    Context context;
    WhatsAppVideos whatsAppVideos;
    ProgressDialog progressDialog;
    public WhatsAppVideosAdapter(List<StatusModel> statusModelList, Context context, WhatsAppVideos whatsAppVideos) {
        StatusModelList = statusModelList;
        this.context = context;
        this.whatsAppVideos = whatsAppVideos;
    }





    @NonNull
    @Override
    public WhatsAppVideosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.custom_videoshow,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WhatsAppVideosAdapter.ViewHolder holder, final int position) {
        StatusModel statusModel=StatusModelList.get(position);
        holder.imageView.setImageBitmap(statusModel.getThumbnail());

        holder.shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareVideo("Select the App to Share Image",StatusModelList.get(position).getPath());
            }
        });

        holder.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(context, R.style.MyAlertDialogStyle);
                progressDialog.setMax(100);
                progressDialog.setMessage("Downloading");
                progressDialog.setTitle("Whatsapp Status");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.show();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            while (progressDialog.getProgress() <= progressDialog
                                    .getMax()) {
                                Thread.sleep(200);
                                handle.sendMessage(handle.obtainMessage());
                                if (progressDialog.getProgress() == progressDialog
                                        .getMax()) {
                                    progressDialog.dismiss();


                                    StatusModel statusModel= StatusModelList.get(position);
                                    if(statusModel!=null){
                                        whatsAppVideos.downloadVideo(statusModel);
                                        Toast.makeText(context, "Download Successfully", Toast.LENGTH_SHORT).show();
                                        refreshAndroidGallery(Uri.fromFile(statusModel.getFile()));

                                    }

                                }
                            }

                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                }).start();
            }
            Handler handle = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    progressDialog.incrementProgressBy(1);
                }
            };


        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, PreviewVideo.class);
               intent.putExtra("Video",StatusModelList.get(position).getPath());
                whatsAppVideos.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return StatusModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        Button saveBtn,shareBtn;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.whatsAppVideo);
            saveBtn=itemView.findViewById(R.id.downloadVideo);
            shareBtn=itemView.findViewById(R.id.shareVideo);
            cardView=itemView.findViewById(R.id.videoCardView);
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

    public void refreshAndroidGallery(Uri fileUri) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Intent mediaScanIntent = new Intent(
                    Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            mediaScanIntent.setData(fileUri);
            context.sendBroadcast(mediaScanIntent);
        } else {
            context.sendBroadcast(new Intent(
                    Intent.ACTION_MEDIA_MOUNTED,
                    Uri.parse("file://" + Environment.getExternalStorageDirectory())));
        }
    }
}
