package com.tapkrill.dailymotionapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.dailymotion.android.player.sdk.PlayerWebView;
import com.tapkrill.dailymotionapp.R;

public class Player extends AppCompatActivity {

String video_ID;
   PlayerWebView playerWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        Intent intent=getIntent();
        video_ID=intent.getStringExtra("video_ID");


        playerWebView=findViewById(R.id.dm_player_web_view);
        playerWebView.load(video_ID);
        playerWebView.setFullscreenButton(true);
        playerWebView.toggleControls();


    }

    @Override
    protected void onPause(){
        super.onPause();
        playerWebView.onPause();
    }

    @Override
    protected void onResume(){
        super.onResume();
        playerWebView.onResume();
    }
}