package com.robinsgl.weatherapp.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.robinsgl.weatherapp.data.OnWeatherResponse;
import com.robinsgl.weatherapp.data.WeatherRepo;
import com.robinsgl.weatherapp.model.WeatherModel;

public class WeatherViewModel extends ViewModel {
    private WeatherRepo weatherRepo;

    public MutableLiveData<WeatherModel> weatherData = new MutableLiveData<>();

    public WeatherViewModel() {
        this.weatherRepo = new WeatherRepo();
    }

    public void findWeatherByCity(String cityName) {
        weatherRepo.getWeather(cityName, new OnWeatherResponse() {
            @Override
            public void onWeatherReceived(WeatherModel weather) {
                weatherData.setValue(weather);
            }

            @Override
            public void onWeatherError(Exception exception) {
                weatherData.setValue(new WeatherModel());
            }
        });
    }
}
