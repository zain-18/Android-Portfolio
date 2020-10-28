package com.hiddenskull.zain_.youtubeplayer.UI;

import android.content.Intent;
import android.os.Bundle;

import com.hiddenskull.zain_.youtubeplayer.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MainActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener,
YouTubePlayer.PlaybackEventListener,YouTubePlayer.PlayerStateChangeListener{
YouTubePlayerView youtubeview;
public static String video_id=null;


String API_KEY="AIzaSyB872psUUaSnXLbnJhWlsajtfffa9xg8H4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        youtubeview=findViewById(R.id.videoView1);
        Intent intent = getIntent();
        video_id = intent.getStringExtra("id");
        youtubeview.initialize(API_KEY,this);

    }



    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
         youTubePlayer.setPlayerStateChangeListener(this);
        youTubePlayer.setPlaybackEventListener(this);
        if(!b){
            youTubePlayer.cueVideo(video_id);
            onPlaying();

        }

    }

    @Override
    public void onPlaying() {
    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onLoaded(String s) {

    }

    @Override
    public void onVideoStarted() {

    }

    @Override
    public void onVideoEnded() {

    }

    @Override
    public void onError(YouTubePlayer.ErrorReason errorReason) {

    }

    @Override
    public void onAdStarted() {

    }

    @Override
    public void onBuffering(boolean b) {

    }

    @Override
    public void onStopped() {

    }

    @Override
    public void onPaused() {

    }

    @Override
    public void onSeekTo(int i) {

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
