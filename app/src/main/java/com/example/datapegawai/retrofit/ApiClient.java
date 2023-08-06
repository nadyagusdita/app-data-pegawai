package com.example.datapegawai.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class ApiClient {
    private static final String BASE_URL = "https://61601920faa03600179fb8d2.mockapi.io/";
    private static final String BASE_URL_WILAYAH  = "https://api.binderbyte.com/wilayah/";
    private static Retrofit retrofit;
    private static Retrofit retrofit2;


    public static Retrofit getClient(){
        if(retrofit == null){
            retrofit = new  Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getClientApiWilayah(){
        if(retrofit2 == null){
            retrofit2 = new  Retrofit.Builder()
                    .baseUrl(BASE_URL_WILAYAH)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit2;
    }
}
