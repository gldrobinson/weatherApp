package com.robinsgl.weatherapp.model;

public class WeatherInfo {
    private int id;
    private String main;
    private String description;

    public WeatherInfo() {
    }

    public WeatherInfo(int id, String main, String description) {
        this.id = id;
        this.main = main;
        this.description = description;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "description='" + description + '\'' +
                '}';
    }
}
