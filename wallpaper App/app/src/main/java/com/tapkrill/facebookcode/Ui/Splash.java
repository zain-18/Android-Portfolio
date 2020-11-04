package com.tapkrill.facebookcode.Ui;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import com.tapkrill.facebookcode.R;
import static java.lang.Thread.sleep;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        Thread thread =new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(2*1000);
                    startActivity(new Intent(Splash.this,MainActivity.class));
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });thread.start();

    }

}
