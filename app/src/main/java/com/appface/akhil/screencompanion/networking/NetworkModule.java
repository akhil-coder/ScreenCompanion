package com.appface.akhil.screencompanion.networking;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkModule {

    public static final String baseUrl = "https://api.themoviedb.org/";
    private static NetworkModule networkModule;
    private Retrofit retrofit;

    public NetworkModule() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized NetworkModule getInstance() {
        if (networkModule == null) {
            networkModule = new NetworkModule();
        }
        return networkModule;
    }

    public APIService getApi() {
        return retrofit.create(APIService.class);
    }
}
