package com.tapkrill.dailymotionapp.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.tapkrill.dailymotionapp.Adapter.PlaylistAdapter;
import com.tapkrill.dailymotionapp.Adapter.SliderAdapter;
import com.tapkrill.dailymotionapp.Interface.ApiService;
import com.tapkrill.dailymotionapp.Model.MainClass;
import com.tapkrill.dailymotionapp.R;
import com.tapkrill.dailymotionapp.Retrofit.ApiUtilis;
import com.tapkrill.dailymotionapp.Retrofit.Constraints;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
RecyclerView recyclerView;
ApiService apiService;
SliderView sliderView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getData();
    }

    private void init(){

        recyclerView=findViewById(R.id.recyclerView);
        sliderView=findViewById(R.id.imageSlider);

    }

    private void getData(){
        apiService= ApiUtilis.getApiService(Constraints.baseUrl);
        apiService.getplaylists("id,description,thumbnail_180_url,name","1","100","x2e885f").enqueue(new Callback<MainClass>() {
            @Override
            public void onResponse(Call<MainClass> call, Response<MainClass> response) {
                if (response.isSuccessful()){
                    MainClass mainClass=response.body();
                    ArrayList<MainClass.List> arrayList= (ArrayList<MainClass.List>) mainClass.getList();
                    PlaylistAdapter playlistAdapter=new PlaylistAdapter(arrayList,MainActivity.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerView.setAdapter(playlistAdapter);
                    playlistAdapter.notifyDataSetChanged();
                    SliderAdapter sliderAdapter=new SliderAdapter(MainActivity.this,arrayList);
                    sliderView.setSliderAdapter(sliderAdapter);
                    //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
                    sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                    sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                    sliderView.setIndicatorSelectedColor(Color.WHITE);
                    sliderView.setIndicatorUnselectedColor(Color.GRAY);
                    sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
                    sliderView.startAutoCycle();


                }

            }

            @Override
            public void onFailure(Call<MainClass> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });


    }

}