package com.robinsgl.weatherapp.network;

import com.robinsgl.weatherapp.model.Weather;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherApi {
    @GET("q={cityName}&appid={APIkey}")
    Call<List<Weather>> getWeatherInfo(@Path("cityName") String cityName, @Path("APIkey") String APIkey);
}
