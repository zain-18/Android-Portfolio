package com.tapkrill.facebookcode.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tapkrill.facebookcode.Adapter.NewWallpaperAdpater;
import com.tapkrill.facebookcode.Interface.ApiService;
import com.tapkrill.facebookcode.Model.Mainclass;
import com.tapkrill.facebookcode.R;
import com.tapkrill.facebookcode.Retrofit.ApiUtils;
import com.tapkrill.facebookcode.Retrofit.BaseUrl;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class New extends Fragment {

    RecyclerView NewWallpaper_rv;
    ApiService apiService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle savedInstanceState){

        //initialinig view
        View view=inflater.inflate(R.layout.news,viewGroup,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view,@Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        //initialize widgets
        NewWallpaper_rv=view.findViewById(R.id.new_recyclerview);

        //calling  wallpaperNew function
        wallpaperNew();


    }


        //Function
    public void wallpaperNew(){

        apiService= ApiUtils.getApiService(BaseUrl.Base_url);
        apiService.getWallpaper("new","40","1").enqueue(new Callback<Mainclass>() {

            @Override
            public void onResponse(Call<Mainclass> call, Response<Mainclass> response) {
                if(response.isSuccessful()){

                    Mainclass mainclass=response.body();

                    //initialize Array list and passing Model class
                    ArrayList<Mainclass.Photo> arrayList= (ArrayList<Mainclass.Photo>) mainclass.getPhotos();

                    //initialize Adapter and passing required parameters init
                    NewWallpaperAdpater newWallpaperAdpater=new NewWallpaperAdpater(getContext(),New.this,arrayList);

                    // Setting Layout Manager & type
                    NewWallpaper_rv.setLayoutManager(new GridLayoutManager(getContext(),2));

                    //set Adapter to recyclerview
                    NewWallpaper_rv.setAdapter(newWallpaperAdpater);

                    //notifyAdapter when data updated
                    newWallpaperAdpater.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<Mainclass> call, Throwable t) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
