package com.hiddenskull.zain_.youtubeplayer.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hiddenskull.zain_.youtubeplayer.Model.Item;
import com.hiddenskull.zain_.youtubeplayer.Model.getYoutubeData;
import com.hiddenskull.zain_.youtubeplayer.R;
import com.hiddenskull.zain_.youtubeplayer.Retrofit.getData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.hiddenskull.zain_.youtubeplayer.R.id.backbtn;
import static com.hiddenskull.zain_.youtubeplayer.R.id.card_recycler_view;

public class Playlist extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DataAdapter adapter;
    public String playlistid,playlisttitle;
    Button Bckbtn;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        recyclerView=findViewById(card_recycler_view);
        Bckbtn=findViewById(backbtn);
        textView=findViewById(R.id.title);
        final Intent intent=getIntent();
        playlistid=intent.getStringExtra("playlistid");
        playlisttitle=intent.getStringExtra("Title");
        textView.setText(playlisttitle);
        initview();

        Bckbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Playlist.this, Category.class);
                startActivity(intent1);
            }
        });
    }
public void initview(){
recyclerView.setHasFixedSize(true);
RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getApplicationContext());
    recyclerView.setLayoutManager(layoutManager);
    Data();
}
public  void Data(){
    Retrofit retrofit=new Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        final getData getdat=retrofit.create(getData.class);
    Call<getYoutubeData> call=getdat.getdata("snippet","50",playlistid,"items/snippet(title,description,thumbnails,resourceId)","AIzaSyCeTdUZ9nD-sjlH051m5mZaxUIyqOaqUsA");
    call.enqueue(new Callback<getYoutubeData>() {
        @Override
        public void onResponse(Call<getYoutubeData> call, Response<getYoutubeData> response) {
            getYoutubeData getYoutubeData= (getYoutubeData) response.body();
            ArrayList<Item> arrayList=new ArrayList<Item>(getYoutubeData.getItems());
            adapter = new DataAdapter(arrayList);
            recyclerView.setAdapter(adapter);
        }

        @Override
        public void onFailure(Call<getYoutubeData> call, Throwable t) {

        }
    });
}
}
