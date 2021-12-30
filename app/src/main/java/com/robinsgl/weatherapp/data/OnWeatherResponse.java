package com.robinsgl.weatherapp.data;

import com.robinsgl.weatherapp.model.WeatherModel;

import org.json.JSONObject;

public interface OnWeatherResponse {
    void onWeatherReceived(JSONObject response);
    void onWeatherError(Exception exception);
}
