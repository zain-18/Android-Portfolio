package com.tapkrill.dailymotionapp.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tapkrill.dailymotionapp.Adapter.PlayListItemsAdapter;
import com.tapkrill.dailymotionapp.Interface.ApiService;
import com.tapkrill.dailymotionapp.R;
import com.tapkrill.dailymotionapp.Retrofit.ApiUtilis;
import com.tapkrill.dailymotionapp.Retrofit.Constraints;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayListItems extends AppCompatActivity {
    RecyclerView playListItem_Recyclerview;
    ApiService apiService;
    String playlist_Id,Url;
    EditText searchView;
    ArrayList<com.tapkrill.dailymotionapp.Model.PlayListItems.List> arrayList;
    PlayListItemsAdapter playListItemsAdapter;
    RelativeLayout relativeLayout1,relativeLayout2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list_items);
        Intent intent=getIntent();
        playlist_Id = intent.getStringExtra("playlistid");
        Url="playlist/"+playlist_Id+"/videos";
        init();
        getPlayListItems();

        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                    filter(editable.toString());
            }
        });
    }

    private void init(){
        playListItem_Recyclerview=findViewById(R.id.playListItem_Recyclerview);
        searchView=findViewById(R.id.searchView);
        relativeLayout1=findViewById(R.id.relativeLayout1);
        relativeLayout2=findViewById(R.id.relativeLayout2);

    }

    private void getPlayListItems(){
        apiService= ApiUtilis.getApiService(Constraints.baseUrl);
        apiService.getPlayListItems(Url,"id,embed_url,embed_html,description,thumbnail_180_url","1","100").enqueue(new Callback<com.tapkrill.dailymotionapp.Model.PlayListItems>() {
            @Override
            public void onResponse(Call<com.tapkrill.dailymotionapp.Model.PlayListItems> call, Response<com.tapkrill.dailymotionapp.Model.PlayListItems> response) {
                if ((response.isSuccessful())){

                    com.tapkrill.dailymotionapp.Model.PlayListItems playListItems=response.body();
                    arrayList= (ArrayList<com.tapkrill.dailymotionapp.Model.PlayListItems.List>) playListItems.getList();
                     playListItemsAdapter=new PlayListItemsAdapter(PlayListItems.this,arrayList);
                    playListItem_Recyclerview.setLayoutManager(new LinearLayoutManager(PlayListItems.this));
                    playListItem_Recyclerview.setAdapter(playListItemsAdapter);

                }
            }

            @Override
            public void onFailure(Call<com.tapkrill.dailymotionapp.Model.PlayListItems> call, Throwable t) {
                Toast.makeText(PlayListItems.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void filter(String text){
        ArrayList<com.tapkrill.dailymotionapp.Model.PlayListItems.List> filterList=new ArrayList<>();

        for (com.tapkrill.dailymotionapp.Model.PlayListItems.List item: arrayList){
            if(item.getDescription().toLowerCase().contains(text.toLowerCase())){
                filterList.add(item);
            }
        }
        playListItemsAdapter.filterList(filterList);
    }

    public void search(View view) {
        relativeLayout1.setVisibility(View.GONE);
        relativeLayout2.setVisibility(View.VISIBLE);
    }

    public void cancel(View view) {
        relativeLayout2.setVisibility(View.GONE);
        relativeLayout1.setVisibility(View.VISIBLE);
    }

    public void onBackPressed(View view) {
        onBackPressed();
    }

   @Override
    public void onBackPressed(){
       super.onBackPressed();
   }
}