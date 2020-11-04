package com.tapkrill.facebookcode.Ui;

import androidx.appcompat.app.AppCompatActivity;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tapkrill.facebookcode.R;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PreviewWallapaper extends AppCompatActivity {
ImageView displayWallpaper;
Button back;
FloatingActionButton setwallpaper,saveBtn;
String Url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_wallapaper);

        //initialize widgets
        init();

        // Intent to get value from previous Activity
        Intent intent=getIntent();
        Url=intent.getStringExtra("Image");

        //set image in imageview with the help of Glide library
        Glide.with(PreviewWallapaper.this).load(Url).into(displayWallpaper);


        // SetWallpaper Button on clicklisterner to call startWall() function
        setwallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startWall();
            }
        });


        //back button onclick to goto previous activity
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        // SaveWallpaper Button on clicklisterner to call startSave() function
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSave();
            }
        });




    }

    public void init(){
        displayWallpaper=findViewById(R.id.displayWallpaper);
        back=findViewById(R.id.back);
        setwallpaper=findViewById(R.id.setwallpaper);
        saveBtn=findViewById(R.id.saveBtn);


    }

    public static Bitmap viewToBitmap(View view,int width,int height){
        Bitmap bm=Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bm);
        view.draw(canvas);
        return bm;
    }

    private void startWall() {
        WallpaperManager wallpaperManager=WallpaperManager.getInstance(getApplicationContext());
        try {
            wallpaperManager.setBitmap(viewToBitmap(displayWallpaper, displayWallpaper.getWidth(),displayWallpaper.getHeight()));
            Toast.makeText(this,"Wallpaper Set",Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startShare() {
        Bitmap bitmap=viewToBitmap(displayWallpaper, displayWallpaper.getWidth(),displayWallpaper.getHeight());
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("image/jpeg");
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        File file=new File(Environment.getExternalStorageDirectory()+File.separator+"ImageDemo.jpg");
        try {
            file.createNewFile();
            FileOutputStream fileOutputStream=new FileOutputStream(file);
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        intent.putExtra(Intent.EXTRA_STREAM,Uri.parse("file:///sdcard/ImageDemo.jpg"));
        startActivity(Intent.createChooser(intent,"Share Image"));

    }

    private void startSave() {
        FileOutputStream fileOutputStream=null;
        File file=getDisc();
        if (!file.exists()&&!file.mkdirs()){
            Toast.makeText(this,"Can't create directory to save image",Toast.LENGTH_SHORT).show();
            return;
        }
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyymmhhssmm");
        String date=simpleDateFormat.format(new Date());
        String name="Img"+date+".jpg";
        String file_name=file.getAbsolutePath()+"/"+name;
        File new_file=new File(file_name);
        try {
            fileOutputStream=new FileOutputStream(new_file);
            Bitmap bitmap=viewToBitmap(displayWallpaper, displayWallpaper.getWidth(),displayWallpaper.getHeight());
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
            Toast.makeText(this,"Save image Succsess",Toast.LENGTH_SHORT).show();
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        refreshGallery(new_file);
    }

    public void refreshGallery(File file){
        Intent intent=new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(file));
        sendBroadcast(intent);
    }

    private File getDisc(){
        File file= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        return new File(file,"Wallpapers");
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Animatoo.animateInAndOut(PreviewWallapaper.this);
    }
}
