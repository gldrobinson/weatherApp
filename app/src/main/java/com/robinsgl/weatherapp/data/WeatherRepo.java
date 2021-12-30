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
    private WeatherModel weather;

    public WeatherModel getWeather(String cityName, OnWeatherResponse callback) {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName +
                "&appid=52d31a4b583f33bfd74b2557fe4e7d20";
        WeatherModel weatherModel = new WeatherModel();
        WeatherInfo weatherInfo = new WeatherInfo();
        WeatherMain weatherMain = new WeatherMain();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                JSONArray jsonArrayWeather = response.getJSONArray("weather");
                JSONObject jsonObjectWeather = jsonArrayWeather.getJSONObject(0);

                weatherInfo.setMain(jsonObjectWeather.getString("main"));
                weatherInfo.setDescription(jsonObjectWeather.getString("description"));

                JSONObject jsonObjectMain = response.getJSONObject("main");

                weatherMain.setTemp(jsonObjectMain.getDouble("temp"));
                weatherMain.setTemp_max(jsonObjectMain.getDouble("temp_max"));
                weatherMain.setTemp_min(jsonObjectMain.getDouble("temp_min"));

                weatherModel.setWeatherMain(weatherMain);
                weatherModel.setWeatherInfo(weatherInfo);

                if (callback != null) {
                    callback.onWeatherReceived(weatherModel);
                }

            } catch (JSONException e) {
                e.printStackTrace();
                callback.onWeatherError(e);
            }
        }, error -> {
            Log.i("WeatherRepo", "error");
        });

        WeatherAppController.getInstance().addToRequestQueue(jsonObjectRequest);
        return weatherModel;

    }

}
