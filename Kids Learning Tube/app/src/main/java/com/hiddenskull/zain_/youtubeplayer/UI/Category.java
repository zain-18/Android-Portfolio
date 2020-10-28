package com.hiddenskull.zain_.youtubeplayer.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.hiddenskull.zain_.youtubeplayer.R;
import com.flurry.android.FlurryAgent;

public class Category extends AppCompatActivity  {
CardView btn1,btn2,btn3,btn4,btn5,btn6;
Button details;
ImageView img1,img2,img3,img4;
public String playlist_id1="PLsujH_u89cJ-1nHtMpSA_7nboI1j11blX";
    public String playlist_id2="PLsujH_u89cJ9QoM8uLggvjM8RYnjv_XQY";
    public String playlist_id3="PLsujH_u89cJ_hRV1hOkBC9HDG2yW3EQC7";
    public String playlist_id4="PLsujH_u89cJ9jJryre7paCN7Sz2_LQaIK";
    public String playlist_id5="PLsujH_u89cJ_-OYp2WypDsz7N8yaVVqoW";
    public String playlist_id6="PLsujH_u89cJ8cB6CrSLxqXyk6vTvIQIzb";
    public String playlist_title1="Animal Sounds Song";
    public String playlist_title2="Animal Sound and more!";
    public String playlist_title3="Zoo Animals for Kids";
    public String playlist_title4="Kid's Animal and Nursery";
    public String playlist_title5="Animals for Kids";
    public String playlist_title6="Learning Animal Sounds";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        new FlurryAgent.Builder()
                .withLogEnabled(true)
                .build(this, "6VJQKB8B9R2NPZRH26Z8");
        btn1=findViewById(R.id.playlist1);
        btn2=findViewById(R.id.playlist2);
        btn3=findViewById(R.id.playlist3);
        btn4=findViewById(R.id.playlist4);
        btn5=findViewById(R.id.playlist5);
        btn6=findViewById(R.id.playlist6);
        details=findViewById(R.id.details);
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Category.this,Details.class);
                startActivity(intent);
            }
        });



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Category.this, Playlist.class);
                intent1.putExtra("playlistid",playlist_id1);
                intent1.putExtra("Title",playlist_title1);
                startActivity(intent1);

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(Category.this,Playlist.class);
                intent2.putExtra("playlistid",playlist_id2);
                intent2.putExtra("Title",playlist_title2);
                startActivity(intent2);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3=new Intent(Category.this,Playlist.class);
                intent3.putExtra("playlistid",playlist_id3);
                intent3.putExtra("Title",playlist_title3);
                startActivity(intent3);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4=new Intent(Category.this,Playlist.class);
                intent4.putExtra("playlistid",playlist_id4);
                intent4.putExtra("Title",playlist_title4);
                startActivity(intent4);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5=new Intent(Category.this,Playlist.class);
                intent5.putExtra("playlistid",playlist_id5);
                intent5.putExtra("Title",playlist_title5);
                startActivity(intent5);

            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent6=new Intent(Category.this,Playlist.class);
                intent6.putExtra("playlistid",playlist_id6);
                intent6.putExtra("Title",playlist_title6);
                startActivity(intent6);
            }
        });
    }
    public void onBackPressed(){
        finish();
        super.onBackPressed();
    }

}
