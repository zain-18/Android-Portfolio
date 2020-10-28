package com.hiddenskull.zain_.youtubeplayer.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hiddenskull.zain_.youtubeplayer.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3*1000);
                        Intent intent=new Intent(Splash.this, Category.class);
                        startActivity(intent);
                        finish();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
    }
}
