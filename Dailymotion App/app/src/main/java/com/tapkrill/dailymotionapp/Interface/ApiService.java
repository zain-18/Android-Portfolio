package com.tapkrill.dailymotionapp.Interface;

import com.tapkrill.dailymotionapp.Model.MainClass;
import com.tapkrill.dailymotionapp.Model.PlayListItems;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiService {

    @GET("playlists")
    Call<MainClass> getplaylists(@Query("fields") String fields
                                ,@Query("page") String page
                                ,@Query("limit") String limit
                                ,@Query("owner") String owner);
    @GET
    Call<PlayListItems> getPlayListItems(@Url String url
                                        ,@Query("fields") String fields
                                        ,@Query("page") String page
                                        ,@Query("limit") String limit);
}
