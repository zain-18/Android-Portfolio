package com.example.whatsapp_status_downloader.Adapters;

import android.app.ProgressDialog;
import android.content.ContentValues;
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

import com.bumptech.glide.Glide;
import com.example.whatsapp_status_downloader.Fragments.WhatsAppImages;
import com.example.whatsapp_status_downloader.Model.StatusModel;
import com.example.whatsapp_status_downloader.R;
import com.example.whatsapp_status_downloader.UI.ViewImage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class WhatsAppImageAdapter extends RecyclerView.Adapter<WhatsAppImageAdapter.ViewHolder> {
private final List<StatusModel> statusList;
Context context;
WhatsAppImages whatsAppImages;
ProgressDialog progressDialog;

    public WhatsAppImageAdapter(List<StatusModel> statusList, Context context, WhatsAppImages whatsAppImages) {
        this.statusList = statusList;
        this.context = context;
        this.whatsAppImages = whatsAppImages;
    }

    @NonNull
    @Override
    public WhatsAppImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.custom_imageshow,parent,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WhatsAppImageAdapter.ViewHolder holder, final int position) {
         StatusModel statusModel=statusList.get(position);
         holder.imageView.setImageBitmap(statusModel.getThumbnail());

         holder.shareBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 shareImages("Select the App to Share Image",statusList.get(position).getPath());

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
                                         whatsAppImages.downloadImage(statusModel);
                                         Toast.makeText(context, "Download Successfully", Toast.LENGTH_SHORT).show();
                                         refreshAndroidGallery(Uri.fromFile(statusList.get(position).getFile()));

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
                 Intent intent=new Intent(context, ViewImage.class);
                 intent.putExtra("Image",statusList.get(position).getPath());
                 whatsAppImages.startActivity(intent);
             }
         });





    }

    @Override
    public int getItemCount() {
        return statusList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        Button saveBtn,shareBtn;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.whatsAppImages);
            saveBtn=itemView.findViewById(R.id.downloadImage);
            shareBtn=itemView.findViewById(R.id.shareImage);
            cardView=itemView.findViewById(R.id.imageCardView);

        }
    }

    public void shareImages(final String title, String path) {

        MediaScannerConnection.scanFile(context, new String[] { path },

                null, new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                        Intent shareIntent = new Intent(
                                android.content.Intent.ACTION_SEND);
                        shareIntent.setType("image/jpeg/png");
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
