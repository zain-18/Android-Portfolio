package com.example.whatsapp_status_downloader.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.example.whatsapp_status_downloader.R;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){

                        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
                            checkAndRequestPermission();
                           // sleep(2*1000);
                        }
                    }
                    sleep(2*1000);
                    startActivity(new Intent(Splash.this,MainActivity.class));
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }); thread.start();
    }

    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;

    public boolean checkAndRequestPermission(){

        int writeExternalStoragePermission= ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int readExternalStoragePermission=ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE);
        List<String> permissionlist= new ArrayList<>();
        if(writeExternalStoragePermission!= PackageManager.PERMISSION_GRANTED){
            permissionlist.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(readExternalStoragePermission!= PackageManager.PERMISSION_GRANTED){
            permissionlist.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        if(!permissionlist.isEmpty()){
            ActivityCompat.requestPermissions(this,permissionlist.toArray(new String[permissionlist.size()]),REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }
}
