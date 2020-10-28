package com.example.whatsapp_status_downloader.Fragments;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.whatsapp_status_downloader.Adapters.WhatsAppImageAdapter;
import com.example.whatsapp_status_downloader.Model.StatusModel;
import com.example.whatsapp_status_downloader.R;
import com.example.whatsapp_status_downloader.Utilis.MyConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;

public class WhatsAppImages extends Fragment {
    RecyclerView recyclerView;
    ProgressBar progressBar;
    Handler handler = new Handler();
    ArrayList<StatusModel> statusModelArrayList;
    WhatsAppImageAdapter whatsAppImageAdapter;
    private SwipeRefreshLayout swipeContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.whatsappimagesfragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.Imagerecyclerview);
        progressBar = view.findViewById(R.id.imagesProgressBar);
        statusModelArrayList = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        getStatusImages();


    }

    private void getStatusImages() {

        if (MyConstants.STATUS_DIRECTORY.exists()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    File[] statusFiles = MyConstants.STATUS_DIRECTORY.listFiles();

                    if (statusFiles != null && statusFiles.length > 0) {
                        Arrays.sort(statusFiles);

                        for (final File statusFile : statusFiles) {
                            StatusModel statusModel = new StatusModel(statusFile,
                                    statusFile.getName(), statusFile.getAbsolutePath());
                            statusModel.setThumbnail(getThumbnail(statusModel));

                            if (!statusModel.isVideo()) {
                                statusModelArrayList.add(statusModel);
                            }

                        }

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                                whatsAppImageAdapter = new WhatsAppImageAdapter(statusModelArrayList, getContext(), WhatsAppImages.this);
                                recyclerView.setAdapter(whatsAppImageAdapter);
                                whatsAppImageAdapter.notifyDataSetChanged();
                            }
                        });
                    } else {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getContext(), "dir not Found", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }).start();
        }


    }

    private Bitmap getThumbnail(StatusModel statusModel) {

        if (statusModel.isVideo()) {
            return ThumbnailUtils.createVideoThumbnail(statusModel.getFile().getAbsolutePath(), MediaStore.Video.Thumbnails.FULL_SCREEN_KIND);
        } else {
            return ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(statusModel.getFile().getAbsolutePath()),
                    MyConstants.THUMBSIZE,
                    MyConstants.THUMBSIZE);
        }
    }


    public void downloadImage(StatusModel statusModel) {

        File file = new File(MyConstants.APP_DIR_IMAGE);
        if (!file.exists()) {
            file.mkdirs();
        }

        File destFile = new File(file + File.separator + statusModel.getTitle());
        if (destFile.exists()) {
            destFile.delete();
        }
        try {
            copyFile(statusModel.getFile(), destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void copyFile(File sourceFile, File destFile) throws IOException {
        if (!destFile.getParentFile().exists())
            destFile.getParentFile().mkdirs();

        if (!destFile.exists()) {
            destFile.createNewFile();
        }

        FileChannel source = null;
        FileChannel destination = null;

        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            destination.transferFrom(source, 0, source.size());
        } finally {
            if (source != null) {
                source.close();
            }
            if (destination != null) {
                destination.close();
            }
        }

    }

    @Override
    public void onResume() {

        super.onResume();
        this.onCreate(null);

    }




}

