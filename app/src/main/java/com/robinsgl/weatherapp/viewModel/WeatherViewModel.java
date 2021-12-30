package com.robinsgl.weatherapp.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.robinsgl.weatherapp.data.OnWeatherResponse;
import com.robinsgl.weatherapp.data.WeatherRepo;
import com.robinsgl.weatherapp.model.WeatherInfo;
import com.robinsgl.weatherapp.model.WeatherMain;
import com.robinsgl.weatherapp.model.WeatherModel;

import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherViewModel extends ViewModel {
    private WeatherRepo weatherRepo;

    public MutableLiveData<WeatherModel> weatherData = new MutableLiveData<>();

    public WeatherViewModel() {
        this.weatherRepo = new WeatherRepo();
    }

    public void findWeatherByCity(String cityName) {
        weatherRepo.getWeather(cityName, new OnWeatherResponse() {
            @Override
            public void onWeatherReceived(JSONObject response) {
                WeatherModel weatherModel = new WeatherModel();
                WeatherInfo weatherInfo = new WeatherInfo();
                WeatherMain weatherMain = new WeatherMain();

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
                } catch (Exception e) {
                    e.printStackTrace();
                }

                weatherData.setValue(weatherModel);
            }

            @Override
            public void onWeatherError(Exception exception) {
                weatherData.setValue(new WeatherModel());
            }
        });
    }
}
