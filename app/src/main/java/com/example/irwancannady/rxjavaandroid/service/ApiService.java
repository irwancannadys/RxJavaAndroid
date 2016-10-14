package com.example.irwancannady.rxjavaandroid.service;

import com.example.irwancannady.rxjavaandroid.model.Github;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import rx.observers.Observers;

/**
 * Created by adhitiahidayat on 10/14/16.
 */

public interface ApiService {

    String SERVICE_END_POINT = "https://api.github.com/";

    @GET("users/{login}")
    Observable<Github> getUser(@Path("login") String login);
}
