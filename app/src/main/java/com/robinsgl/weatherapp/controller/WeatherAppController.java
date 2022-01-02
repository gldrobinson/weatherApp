package com.robinsgl.weatherapp.controller;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class WeatherAppController extends Application {
    private static WeatherAppController instance;
    private RequestQueue requestQueue;

    public static synchronized WeatherAppController getInstance() {
        if (instance == null) {
            instance = new WeatherAppController();
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }


}
