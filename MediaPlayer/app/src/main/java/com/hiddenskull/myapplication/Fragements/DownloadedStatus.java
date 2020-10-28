package com.hiddenskull.myapplication.Fragements;

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

import com.hiddenskull.myapplication.Adapter.DownloadedAdapter;
import com.hiddenskull.myapplication.Model.DownloadModel;
import com.hiddenskull.myapplication.R;
import com.hiddenskull.myapplication.Utils.MyConstants;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class DownloadedStatus extends Fragment {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    Handler handler=new Handler();
    ArrayList<DownloadModel> downloadModelArrayListArrayList;
    DownloadedAdapter downloadedAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.downloadedstatus,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.downlodedrecyclerview);
        progressBar=view.findViewById(R.id.progressBar);
        downloadModelArrayListArrayList=new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));

        getStatusVideos();
    }

    private void getStatusVideos() {

        if(MyConstants.Saved_Status_Directory.exists()){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    File[] statusFiles= MyConstants.Saved_Status_Directory.listFiles();

                    if(statusFiles!=null &&statusFiles.length>0){
                        Arrays.sort(statusFiles);

                        for (final File statusFile:statusFiles){
                            DownloadModel downloadModel=new DownloadModel(statusFile,
                                    statusFile.getName(),statusFile.getAbsolutePath());
                            downloadModel.setThumbnail(getThumbnail(downloadModel));

                            if(downloadModel.isVideo()){
                                downloadModelArrayListArrayList.add(downloadModel);
                            }

                        }

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                                downloadedAdapter=new DownloadedAdapter(downloadModelArrayListArrayList,getContext(),DownloadedStatus.this);
                                recyclerView.setAdapter(downloadedAdapter);
                                downloadedAdapter.notifyDataSetChanged();

                            }
                        });
                    }
                    else {
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

    private Bitmap getThumbnail(DownloadModel downloadModel) {

        if(downloadModel.isVideo()){
            return ThumbnailUtils.createVideoThumbnail(downloadModel.getFile().getAbsolutePath(), MediaStore.Video.Thumbnails.MICRO_KIND);
        }
        else {
            return ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(downloadModel.getFile().getAbsolutePath()),
                    MyConstants.THUMBSIZES,
                    MyConstants.THUMBSIZES);
        }
    }

    @Override
    public void onResume() {

        super.onResume();
        this.onCreate(null);
    }
}
