package com.hiddenskull.myapplication.UI;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hiddenskull.myapplication.Adapter.VideosAdapter;
import com.hiddenskull.myapplication.Model.VideosModel;
import com.hiddenskull.myapplication.R;
import com.hiddenskull.myapplication.Utils.MyConstants;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Videos extends AppCompatActivity {
public File pathFile;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    Handler handler=new Handler();
    ArrayList<VideosModel> VideosModelListArrayList;
    VideosAdapter videosAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        Intent getpath=getIntent();
        getpath.getStringExtra("pathname");
        pathFile=new File(getpath.getStringExtra("pathname"));

        recyclerView=findViewById(R.id.videos_recyclerview);
        progressBar=findViewById(R.id.videos_progressBar);
        VideosModelListArrayList=new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(Videos.this,3));
        getStatusVideos();
    }

    private void getStatusVideos() {

        if(pathFile.exists()){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    File[] statusFiles= pathFile.listFiles();

                    if(statusFiles!=null &&statusFiles.length>0){
                        Arrays.sort(statusFiles);

                        for (final File statusFile:statusFiles){
                            VideosModel videosModel=new VideosModel(statusFile,
                                    statusFile.getName(),statusFile.getAbsolutePath());
                            videosModel.setThumbnail(getThumbnail(videosModel));

                            if(videosModel.isVideo()){
                                VideosModelListArrayList.add(videosModel);
                            }

                        }

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                                videosAdapter=new VideosAdapter(VideosModelListArrayList,Videos.this);
                                recyclerView.setAdapter(videosAdapter);
                                videosAdapter.notifyDataSetChanged();

                            }
                        });
                    }
                    else {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(Videos.this, "dir not Found", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }).start();
        }
    }

    private Bitmap getThumbnail(VideosModel videosModel) {

        if(videosModel.isVideo()){
            return ThumbnailUtils.createVideoThumbnail(videosModel.getFile().getAbsolutePath(), MediaStore.Video.Thumbnails.MICRO_KIND);
        }
        else {
            return ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(videosModel.getFile().getAbsolutePath()),
                    MyConstants.THUMBSIZES,
                    MyConstants.THUMBSIZES);
        }
    }

    public void back(View view) {

        startActivity(new Intent(Videos.this,MainActivity.class));
        finish();
    }

    public void onBackPressed() {

        startActivity(new Intent(Videos.this,MainActivity.class));
        finish();
        return;
    }
}
