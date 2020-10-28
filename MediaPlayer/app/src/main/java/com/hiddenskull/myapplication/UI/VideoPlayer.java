package com.hiddenskull.myapplication.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hiddenskull.myapplication.R;
import com.khizar1556.mkvideoplayer.MKPlayer;

public class VideoPlayer extends AppCompatActivity {
String url,title;
MKPlayer mkPlayer;
TextView playerTitle;
Button PlayerbackBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        playerTitle=findViewById(R.id.playerTitle);
        PlayerbackBtn=findViewById(R.id.PlayerbackBtn);
        Intent intent=getIntent();
        url=intent.getStringExtra("video");
        title=intent.getStringExtra("title");
        playerTitle.setText(title);
        mkPlayer=new MKPlayer(VideoPlayer.this);
        mkPlayer.play(url);

        mkPlayer.setPlayerCallbacks(new MKPlayer.playerCallbacks() {
            @Override
            public void onNextClick() {
               if(mkPlayer.isPlaying()){
                   mkPlayer.stop();
                   mkPlayer.play(url);
               }
            }

            @Override
            public void onPreviousClick() {
                if(mkPlayer.isPlaying()){
                    mkPlayer.stop();
                    mkPlayer.play(url);
                }
            }
        });

        PlayerbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public  void onBackPressed(){
        super.onBackPressed();
        if(mkPlayer.isPlaying()){
            mkPlayer.stop();
        }
    }
    public void onPause(){
        super.onPause();
        if(mkPlayer.isPlaying()){
            mkPlayer.stop();
        }
    }
}
