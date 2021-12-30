package com.robinsgl.weatherapp.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.robinsgl.weatherapp.controller.WeatherAppController;
import com.robinsgl.weatherapp.model.WeatherInfo;
import com.robinsgl.weatherapp.model.WeatherMain;
import com.robinsgl.weatherapp.model.WeatherModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherRepo {

    public void getWeather(String cityName, OnWeatherResponse callback) {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName +
                "&appid=52d31a4b583f33bfd74b2557fe4e7d20";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                if (callback != null) {
                    callback.onWeatherReceived(response);
                }

            } catch (Exception e) {
                e.printStackTrace();
                callback.onWeatherError(e);
            }
        }, error -> {
            Log.i("WeatherRepo", "error");
        });

        WeatherAppController.getInstance().addToRequestQueue(jsonObjectRequest);

    }

}
