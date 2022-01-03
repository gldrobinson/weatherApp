package com.robinsgl.weatherapp.model;

public class WeatherModel {

    private WeatherInfo weatherInfo;
    private WeatherMain weatherMain;

    public WeatherModel() {
    }

    public WeatherInfo getWeatherInfo() {
        return weatherInfo;
    }

    public void setWeatherInfo(WeatherInfo weatherInfo) {
        this.weatherInfo = weatherInfo;
    }

    public WeatherMain getWeatherMain() {
        return weatherMain;
    }

    public void setWeatherMain(WeatherMain weatherMain) {
        this.weatherMain = weatherMain;
    }

    @Override
    public String toString() {
        return "WeatherModel{" +
                "weatherInfo=" + weatherInfo +
                ", weatherMain=" + weatherMain +
                '}';
    }
}
