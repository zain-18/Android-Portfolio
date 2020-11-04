package com.tapkrill.facebookcode.Retrofit;


import com.tapkrill.facebookcode.Interface.ApiService;

public class ApiUtils {

    private ApiUtils(){}

    public static ApiService getApiService(String baseurl){
        return RetrofitClient.getRetrofit(baseurl).create(ApiService.class);
    }

}
