package com.example.irwancannady.rxjavaandroid.service;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by irwancannady on 10/14/16.
 */

public class ServiceFactory {

    public static <T> T createRetrofitService(final Class<T> clazz, final String endPoint){

        OkHttpClient httpClient = new OkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(endPoint)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient)
                .build();

        T service = retrofit.create(clazz);

        return service;
    }
}
