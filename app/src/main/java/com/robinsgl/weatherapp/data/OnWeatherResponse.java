package com.robinsgl.weatherapp.data;

import com.robinsgl.weatherapp.model.WeatherModel;

public interface OnWeatherResponse {
    void onWeatherReceived(WeatherModel weather);
    void onWeatherError(Exception exception);
}
