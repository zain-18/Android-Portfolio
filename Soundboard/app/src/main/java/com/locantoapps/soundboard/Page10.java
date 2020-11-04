package com.locantoapps.soundboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*import com.applovin.adview.AppLovinIncentivizedInterstitial;*/

public class Page10 extends AppCompatActivity implements View.OnClickListener {
    Button button1,button2,button3,button4,button5,button6,button7,button8,button9,mute,previous;
    MediaPlayer player1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page10);
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
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                if (player1 != null && player1.isPlaying()) {
                    player1.stop();
                }
                player1 = MediaPlayer.create(Page10.this, R.raw.dramatic_end);
                player1.start();

                break;
            case R.id.button2:
                if (player1 != null && player1.isPlaying()) {
                    player1.stop();
                }

                player1 = MediaPlayer.create(Page10.this, R.raw.dramatic_tension);
                player1.start();

                break;
            case R.id.button3:

                if (player1 != null && player1.isPlaying()) {
                    player1.stop();
                }

                player1 = MediaPlayer.create(Page10.this, R.raw.drumroll);
                player1.start();

                break;
            case R.id.button4:
                if (player1 != null && player1.isPlaying()) {
                    player1.stop();
                }

                player1 = MediaPlayer.create(Page10.this, R.raw.eagames);
                player1.start();

                break;
            case R.id.button5:
                if (player1 != null && player1.isPlaying()) {
                    player1.stop();
                }

                player1 = MediaPlayer.create(Page10.this, R.raw.easports);
                player1.start();

                break;
            case R.id.button6:
                if (player1 != null && player1.isPlaying()) {
                    player1.stop();
                }

                player1 = MediaPlayer.create(Page10.this, R.raw.elevator);
                player1.start();
                break;
            case R.id.button7:
                if (player1 != null && player1.isPlaying()) {
                    player1.stop();
                }

                player1 = MediaPlayer.create(Page10.this, R.raw.estornudo);
                player1.start();
                break;
            case R.id.button8:
                if (player1 != null && player1.isPlaying()) {
                    player1.stop();
                }

                player1 = MediaPlayer.create(Page10.this, R.raw.esunamierda);
                player1.start();
                break;
            case R.id.button9:
                if (player1 != null && player1.isPlaying()) {
                    player1.stop();
                }
                player1 = MediaPlayer.create(Page10.this, R.raw.evil_morty);
                player1.start();
                break;
            case R.id.previous:
                Intent intent=new Intent(Page10.this,Page9.class);
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
