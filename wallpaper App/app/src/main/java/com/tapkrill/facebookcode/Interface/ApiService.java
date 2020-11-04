package com.tapkrill.facebookcode.Interface;

import com.tapkrill.facebookcode.Model.Mainclass;

import retrofit2.Call;
import retrofit2.http.GET;

import retrofit2.http.Headers;
import retrofit2.http.Query;


public interface ApiService {

    @Headers("Authorization: 563492ad6f917000010000013cdc646c4a384ad5ac255610b6d00748")
    @GET("search")
    Call<Mainclass> getWallpaper(@Query("query") String query,@Query("per_page") String per_page,@Query("page")String page);



}
