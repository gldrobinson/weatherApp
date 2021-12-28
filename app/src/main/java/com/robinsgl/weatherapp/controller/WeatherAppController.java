package com.robinsgl.weatherapp.controller;

import android.app.Application;
import android.content.Context;

import com.robinsgl.weatherapp.network.Api;
import com.robinsgl.weatherapp.network.WeatherApi;

public class WeatherAppController extends Application {
    private WeatherApi weatherApi;

    private static WeatherAppController get(Context context) {
        return (WeatherAppController) context.getApplicationContext();
    }

    private static WeatherAppController create(Context context) {
        return WeatherAppController.get(context);
    }

    public WeatherApi getWeatherApi() {
        if (weatherApi == null) {
            weatherApi = Api.create();
        }

        return weatherApi;
    }

}
