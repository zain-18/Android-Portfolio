package com.example.whatsapp_status_downloader.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.whatsapp_status_downloader.R;
import com.khizar1556.mkvideoplayer.MKPlayer;


public class PreviewVideo extends AppCompatActivity {
  MKPlayer mkPlayer;
  String Url;
  ImageButton backImageBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_video);
        backImageBtn=findViewById(R.id.BackBtn);
        Intent intent=getIntent();
        Url=intent.getStringExtra("Video");
       mkPlayer=new MKPlayer(PreviewVideo.this);
       mkPlayer.play(Url);
       mkPlayer.setPlayerCallbacks(new MKPlayer.playerCallbacks() {
           @Override
           public void onNextClick() {

               mkPlayer.forward(0.5f);

           }

           @Override
           public void onPreviousClick() {
                if(mkPlayer.isPlaying()){
                    mkPlayer.stop();
                    mkPlayer.play(Url);

                }
           }
       });
       backImageBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(PreviewVideo.this,MainActivity.class));
               finish();
           }
       });


    }

    public void onPause(){
        super.onPause();
        if(mkPlayer.isPlaying()){
            mkPlayer.stop();
        }

    }

    public void onResume(){
        super.onResume();

    }

    public void onBackPressed(){
        super.onBackPressed();
        if (mkPlayer.isPlaying()){
            mkPlayer.stop();
            finish();
        }

    }
}
