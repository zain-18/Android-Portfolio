package com.example.whatsapp_status_downloader.Fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
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

import com.example.whatsapp_status_downloader.Adapters.WhatsAppImageAdapter;
import com.example.whatsapp_status_downloader.Adapters.WhatsAppVideosAdapter;
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

public class WhatsAppVideos extends Fragment {
    RecyclerView recyclerView;
    ProgressBar progressBar;
    Handler handler=new Handler();
    ArrayList<StatusModel> arrayList;
    WhatsAppVideosAdapter whatsAppVideosAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.whatsappvideosfragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.Videorecyclerview);
        progressBar=view.findViewById(R.id.videoProgressBar);
        arrayList=new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        getStatusVideos();

    }
    private void getStatusVideos() {

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

                            if (statusModel.isVideo()) {
                                arrayList.add(statusModel);
                            }

                        }

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                                whatsAppVideosAdapter = new WhatsAppVideosAdapter(arrayList, getContext(), WhatsAppVideos.this);
                                recyclerView.setAdapter(whatsAppVideosAdapter);
                                whatsAppVideosAdapter.notifyDataSetChanged();
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
            return ThumbnailUtils.createVideoThumbnail(statusModel.getFile().getAbsolutePath(), MediaStore.Video.Thumbnails.MICRO_KIND);
        } else {
            return ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(statusModel.getFile().getAbsolutePath()),
                    MyConstants.THUMBSIZE,
                    MyConstants.THUMBSIZE);
        }
    }

    public void downloadVideo(StatusModel statusModel) {

        File file = new File(MyConstants.APP_DIR_VIDEO);
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








}
