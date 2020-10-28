package com.hiddenskull.myapplication.UI;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hiddenskull.myapplication.Adapter.FolderNameAdapter;
import com.hiddenskull.myapplication.Model.FolderName;
import com.hiddenskull.myapplication.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    RelativeLayout relativeLayout;
    RecyclerView recyclerView;
   RecyclerView.LayoutManager layoutManager;
   Button setLayout,aboutnav;
   int spanCount=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(spanCount==3){
                    view.setBackgroundDrawable(getResources().getDrawable(R.drawable.list));
                    spanCount=1;
                    layoutManager=new GridLayoutManager(getApplicationContext(),spanCount);
                    recyclerView.setLayoutManager(layoutManager);
                }
                else if(spanCount==1){
                    view.setBackgroundDrawable(getResources().getDrawable(R.drawable.grid));
                    spanCount=3;
                    layoutManager=new GridLayoutManager(getApplicationContext(),spanCount);
                    recyclerView.setLayoutManager(layoutManager);
                }
            }
        });

        aboutnav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu=new PopupMenu(MainActivity.this,view);
                popupMenu.getMenuInflater().inflate(R.menu.aboutus,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(MainActivity.this);
                popupMenu.show();
            }
        });
    }
    public void init(){
        setLayout=findViewById(R.id.setLayout);
        aboutnav=findViewById(R.id.aboutnav);
        recyclerView=findViewById(R.id.recyclerview);
        relativeLayout=findViewById(R.id.layout);
        layoutManager=new GridLayoutManager(getApplicationContext(),spanCount);
        recyclerView.setLayoutManager(layoutManager);
        getVideoPaths();

    }

    private ArrayList<FolderName> getVideoPaths(){
        ArrayList<FolderName> videoFolders = new ArrayList<>();
        ArrayList<String> videoPaths = new ArrayList<>();
        Uri allVideosuri = android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] projection = { MediaStore.Video.VideoColumns.DATA ,MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media.BUCKET_DISPLAY_NAME,MediaStore.Video.Media.BUCKET_ID};
        Cursor cursor = getContentResolver().query(allVideosuri, projection, null, null, null);

        try {
            cursor.moveToFirst();
            do{

                FolderName folds = new FolderName();

                String name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME));
                String folder = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME));
                String datapath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));

                String folderpaths =  datapath.replace(name,"");

                if (!videoPaths.contains(folderpaths)) {
                    videoPaths.add(folderpaths);
                    folds.setPath(folderpaths);
                    folds.setName(folder);
                    videoFolders.add(folds);
                }

            }while(cursor.moveToNext());

            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(int i = 0;i < videoFolders.size();i++){
            FolderNameAdapter folderNameAdapter=new FolderNameAdapter(videoFolders,this);
            recyclerView.setAdapter(folderNameAdapter);
        }

        return videoFolders;
    }

    public void cancel(View view) {

        relativeLayout.setVisibility(View.GONE);
    }

    public void Next(View view) {

        startActivity(new Intent(MainActivity.this,MainMenu.class));
    }


    public void onBackPressed() {

        Intent setIntent = new Intent(Intent.ACTION_MAIN);
        setIntent.addCategory(Intent.CATEGORY_HOME);
        setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(setIntent);

        return;
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.about_us:
                startActivity(new Intent(MainActivity.this,Aboutus.class));
                return true;
        }
        return true;
    }
}

