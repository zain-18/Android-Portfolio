package com.hiddenskull.myapplication.Adapter;

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

import com.hiddenskull.myapplication.Fragements.WhatsAppStatusFragment;
import com.hiddenskull.myapplication.Model.StatusModel;
import com.hiddenskull.myapplication.R;
import com.hiddenskull.myapplication.UI.VideoPlayer;

import java.util.List;

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.ViewHolder> {

    private final List<StatusModel> statusList;
    Context context;
    WhatsAppStatusFragment whatsAppStatusFragment;
    ProgressDialog progressDialog;
    public StatusAdapter(List<StatusModel> statusList, Context context, WhatsAppStatusFragment whatsAppStatusFragment) {
        this.statusList = statusList;
        this.context = context;
        this.whatsAppStatusFragment = whatsAppStatusFragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.custom_status_videos,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        StatusModel statusModel=statusList.get(position);
        holder.imageView.setImageBitmap(statusModel.getThumbnail());
       // holder.cardView.setAlpha(0);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, VideoPlayer.class);
                intent.putExtra("video",statusList.get(position).getPath());
                whatsAppStatusFragment.startActivity(intent);
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
                                    StatusModel statusModel= statusList.get(position);
                                    if(statusModel!=null){
                                        whatsAppStatusFragment.downloadVideo(statusModel);
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

    }



    @Override
    public int getItemCount() {
        return statusList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        CardView cardView;
        Button shareBtn,saveBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.iv_images);
            cardView=itemView.findViewById(R.id.statuscardview);
            saveBtn=itemView.findViewById(R.id.save_StatusBtn);

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
