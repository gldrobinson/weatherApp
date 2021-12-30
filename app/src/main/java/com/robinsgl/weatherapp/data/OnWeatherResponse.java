package com.robinsgl.weatherapp.data;

import com.robinsgl.weatherapp.model.WeatherModel;

public interface WeatherResponse {
    void processFinish(WeatherModel weather);
}
