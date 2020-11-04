package com.tapkrill.facebookcode.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tapkrill.facebookcode.Adapter.PopularWallpaperAdpater;
import com.tapkrill.facebookcode.Interface.ApiService;
import com.tapkrill.facebookcode.Model.Mainclass;
import com.tapkrill.facebookcode.R;
import com.tapkrill.facebookcode.Retrofit.ApiUtils;
import com.tapkrill.facebookcode.Retrofit.BaseUrl;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Popular extends Fragment {
    RecyclerView recyclerView;
    ApiService apiService;
    PopularWallpaperAdpater popularWallpaperAdpater;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //initialinig view
        View view = inflater.inflate(R.layout.popular, container, false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //initialize widgets
        recyclerView = view.findViewById(R.id.popular_recyclerview);

        //calling  popularApiWallpaper function
        popularApiWallpaper();







    }

    private void popularApiWallpaper(){
        apiService= ApiUtils.getApiService(BaseUrl.Base_url);
        apiService.getWallpaper("popular","80","1").enqueue(new Callback<Mainclass>() {
            @Override
            public void onResponse(Call<Mainclass> call, Response<Mainclass> response) {
                if (response.isSuccessful()){

                    Mainclass mainclass= response.body();

                    //initialize Array list and passing Model class
                    ArrayList<Mainclass.Photo> popularArrayList= (ArrayList<Mainclass.Photo>) mainclass.getPhotos();

                    //initialize Adapter and passing required parameters init
                    popularWallpaperAdpater=new PopularWallpaperAdpater(getContext(),popularArrayList,Popular.this);

                    // Setting Layout Manager & type
                    recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

                    //set Adapter to recyclerview
                    recyclerView.setAdapter(popularWallpaperAdpater);

                    //notifyAdapter when data updated
                    popularWallpaperAdpater.notifyDataSetChanged();

                }
                else {
                    Toast.makeText(getContext(), "Not Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Mainclass> call, Throwable t) {

                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();

            }
        });
    }



    }




