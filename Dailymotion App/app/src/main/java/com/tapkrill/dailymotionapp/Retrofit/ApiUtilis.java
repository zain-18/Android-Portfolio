package com.tapkrill.dailymotionapp.Retrofit;

import com.tapkrill.dailymotionapp.Interface.ApiService;

public class ApiUtilis {

    private ApiUtilis(){}
    public static ApiService getApiService(String baseurl){
        return RetrofitClient.getRetrofit(baseurl).create(ApiService.class);
    }
}
