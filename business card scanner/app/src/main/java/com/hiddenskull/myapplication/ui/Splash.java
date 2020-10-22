package com.hiddenskull.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.hiddenskull.myapplication.Fragments.ThirdFragment;
import com.hiddenskull.myapplication.R;

import static java.lang.Thread.sleep;

public class Splash extends AppCompatActivity {
SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sharedPreferences=getSharedPreferences("pref",MODE_PRIVATE);
        boolean firstStart=sharedPreferences.getBoolean("firstStart",true);
        if (firstStart){
            onboarding();
        }
        else{
            CameraPermission();
            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        sleep(2*1000);
                        Intent intent=new Intent(Splash.this,MainActivity.class);
                        finish();
                        startActivity(intent);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });thread.start();
        }


    }
    public void onboarding(){
        CameraPermission();
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(2*1000);
                    SharedPreferences sharedPreferences=getSharedPreferences("pref",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putBoolean("firstStart",false);
                    editor.apply();
                    startActivity( new Intent(Splash.this,onboarding.class));
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });thread.start();
    }
    private void CameraPermission() {
        String permission = Manifest.permission.CAMERA;
        int grant = ContextCompat.checkSelfPermission(this, permission);
        if ( grant != PackageManager.PERMISSION_GRANTED) {
            String[] permission_list = new String[1];
            permission_list[0] = permission;
            ActivityCompat.requestPermissions(this, permission_list, 1);
        }
    }
}
