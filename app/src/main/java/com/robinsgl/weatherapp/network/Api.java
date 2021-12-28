package com.robinsgl.weatherapp.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    public static WeatherApi create() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("api.openweathermap.org/data/2.5/weather")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(WeatherApi.class);
    }
}
