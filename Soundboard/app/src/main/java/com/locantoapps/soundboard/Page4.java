package com.locantoapps.soundboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*
import com.applovin.adview.AppLovinIncentivizedInterstitial;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinSdk;
*/

public class Page4 extends AppCompatActivity implements View.OnClickListener {
    Button button1,button2,button3,button4,button5,button6,button7,button8,button9,mute,next,previous;
MediaPlayer player1;
    //private AppLovinIncentivizedInterstitial myIncent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page4);
        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(this);
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(this);
        /*--------------------*/
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        /*--------------------*/
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        /*--------------------*/
        mute = findViewById(R.id.mute);
        previous=findViewById(R.id.previous);
        previous.setOnClickListener(this);
        mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopPlaying();

            }
        });
        /*--------nextActivity+Adds-----------*/
        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if (myIncent.isAdReadyToDisplay()) {
                    // A rewarded video is available.  Call the show method with the listeners you want to use.
                    // We will use the display listener to preload the next rewarded video when this one finishes.

                    myIncent.show(Page4.this, null, null, new AppLovinAdDisplayListener() {
                        @Override
                        public void adDisplayed(AppLovinAd appLovinAd) {


                        }

                        @Override
                        public void adHidden(AppLovinAd appLovinAd) {
                            // A rewarded video was closed.  Preload the next video now.  We won't use a load listener.
                            myIncent.preload(null);
                        }
                    });
                } else {
                            *//*Intent intent=new Intent(Page4.this,Page5.class);
                            startActivity(intent);*//*
                    // No ad is currently available.  Perform failover logic...
                }*/
                Intent intent=new Intent(Page4.this,Page5.class);
                startActivity(intent);

            }
        });
        /*---------end of next Activity----------*/
        /*----------Initailization Applovin---------*/
        /*AppLovinSdk.initializeSdk(this);
        myIncent = AppLovinIncentivizedInterstitial.create(this);
        myIncent.preload(null);*/
        /*--------- End of Initailization Applovin----------*/
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                if (player1 != null && player1.isPlaying()) {
                    player1.stop();
                }
                player1 = MediaPlayer.create(Page4.this, R.raw.bendiciones);
                player1.start();

                break;
            case R.id.button2:
                if (player1 != null && player1.isPlaying()) {
                    player1.stop();
                }

                player1 = MediaPlayer.create(Page4.this, R.raw.beso);
                player1.start();

                break;
            case R.id.button3:

                if (player1 != null && player1.isPlaying()) {
                    player1.stop();
                }

                player1 = MediaPlayer.create(Page4.this, R.raw.bigbangtheory);
                player1.start();

                break;
            case R.id.button4:
                if (player1 != null && player1.isPlaying()) {
                    player1.stop();
                }

                player1 = MediaPlayer.create(Page4.this, R.raw.bigsmokeohmygod);
                player1.start();

                break;
            case R.id.button5:
                if (player1 != null && player1.isPlaying()) {
                    player1.stop();
                }

                player1 = MediaPlayer.create(Page4.this, R.raw.bongofeet);
                player1.start();

                break;
            case R.id.button6:
                if (player1 != null && player1.isPlaying()) {
                    player1.stop();
                }

                player1 = MediaPlayer.create(Page4.this, R.raw.boomheadshot);
                player1.start();
                break;
            case R.id.button7:
                if (player1 != null && player1.isPlaying()) {
                    player1.stop();
                }

                player1 = MediaPlayer.create(Page4.this, R.raw.boomplanted);
                player1.start();
                break;
            case R.id.button8:
                if (player1 != null && player1.isPlaying()) {
                    player1.stop();
                }

                player1 = MediaPlayer.create(Page4.this, R.raw.boxeo);
                player1.start();
                break;
            case R.id.button9:
                if (player1 != null && player1.isPlaying()) {
                    player1.stop();
                }
                player1 = MediaPlayer.create(Page4.this, R.raw.breaking_bad_intro);
                player1.start();
                break;
            case R.id.previous:
                Intent intent=new Intent(Page4.this,Page3.class);
                startActivity(intent);
                break;


        }
    }

    private void stopPlaying() {
        if (player1 != null && player1.isPlaying()) {
            player1.stop();
        }
    }
    @Override
    protected void onPause() {
        if(player1!=null){
            player1.stop();
            player1.release();
        }
        super.onPause();
    }
}