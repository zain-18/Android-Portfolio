package com.tapkrill.facebookcode.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.tapkrill.facebookcode.Adapter.SelectedCategoryAdapter;
import com.tapkrill.facebookcode.Interface.ApiService;
import com.tapkrill.facebookcode.Model.Mainclass;
import com.tapkrill.facebookcode.R;
import com.tapkrill.facebookcode.Retrofit.ApiUtils;
import com.tapkrill.facebookcode.Retrofit.BaseUrl;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectedCategory extends AppCompatActivity {

RecyclerView selectedcategory_recyclerview;
ApiService apiService;
String category;
TextView categoryTitle;
Button categoryback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_category);

        //initialize widgets
        init();

        // Intent to get value from previous Activity
        Intent intent=getIntent();
        category=intent.getStringExtra("title");

        //set Title in categoryTitle textview
        categoryTitle.setText(category);

        //calling wallpaperCategory() by passing string parameter
        wallpaperCategory(category);


        //categoryback button onclick to goto previous activity
        categoryback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }



    public void wallpaperCategory(String Category){

        apiService= ApiUtils.getApiService(BaseUrl.Base_url);
        apiService.getWallpaper(Category,"40","1").enqueue(new Callback<Mainclass>() {
            @Override
            public void onResponse(Call<Mainclass> call, Response<Mainclass> response) {
                if(response.isSuccessful()){
                    Mainclass mainclass=response.body();
                    ArrayList<Mainclass.Photo> arrayList= (ArrayList<Mainclass.Photo>) mainclass.getPhotos();
                    SelectedCategoryAdapter selectedCategoryAdapter=new SelectedCategoryAdapter(SelectedCategory.this,arrayList);
                    selectedcategory_recyclerview.setLayoutManager(new GridLayoutManager(SelectedCategory.this,2));
                    selectedcategory_recyclerview.setAdapter(selectedCategoryAdapter);
                    selectedCategoryAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<Mainclass> call, Throwable t) {

            }
        });
    }

    public void init(){
        categoryback=findViewById(R.id.category_back);
        selectedcategory_recyclerview=findViewById(R.id.selectedcategory_recyclerview);
        categoryTitle=findViewById(R.id.categoryTitle);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Animatoo.animateInAndOut(SelectedCategory.this);
    }
}
