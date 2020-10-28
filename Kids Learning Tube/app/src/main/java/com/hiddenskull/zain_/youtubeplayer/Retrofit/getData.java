package com.hiddenskull.zain_.youtubeplayer.Retrofit;

import com.hiddenskull.zain_.youtubeplayer.Model.getYoutubeData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface getData {
    @GET("youtube/v3/playlistItems")
        Call<getYoutubeData>getdata(@Query("part") String part,
                                    @Query("maxResults") String maxResults,
                                    @Query("playlistId") String playlistId,
                                    @Query("fields") String fields,
                                    @Query("key") String key);
}
