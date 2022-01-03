package com.robinsgl.weatherapp.data;

import org.json.JSONObject;

public interface OnWeatherResponse {
    void onWeatherReceived(JSONObject response);
    void onWeatherError(Exception exception);
}
