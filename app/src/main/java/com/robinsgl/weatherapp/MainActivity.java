package com.robinsgl.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.robinsgl.weatherapp.viewModel.WeatherViewModel;

public class MainActivity extends AppCompatActivity {

    WeatherViewModel weatherViewModel;

    Button findWeatherButton;
    EditText citySearch;
    TextView cityHeader;
    TextView tempText;
    TextView tempMax;
    TextView tempMin;
    TextView mainText;
    ImageView weatherIcon;
    ImageView tempIcon;
    TextView searchedCity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findWeatherButton = findViewById(R.id.search_button);
        citySearch = findViewById(R.id.city_search_edit_text);
        cityHeader = findViewById(R.id.city_header_text_view);
        tempText = findViewById(R.id.temp_text_view);
        tempMax = findViewById(R.id.temp_max_text_view);
        tempMin = findViewById(R.id.temp_min_text_view);
        mainText = findViewById(R.id.main_text_view);
        weatherIcon = (ImageView) findViewById(R.id.weather_icon);
        tempIcon = findViewById(R.id.temp_icon);
        searchedCity = findViewById(R.id.city_searched_text_view);

        weatherViewModel = new WeatherViewModel();

        weatherViewModel.weatherData.observe(this, weatherModel -> {
            int temp = (int) ( weatherModel.getWeatherMain().getTemp() - 272.15 );
            int temp_max = (int) (weatherModel.getWeatherMain().getTemp_max() - 272.15);
            int temp_min = (int) (weatherModel.getWeatherMain().getTemp_min() - 272.15);

            searchedCity.setText(citySearch.getText().toString());
            searchedCity.setVisibility(View.VISIBLE);
            citySearch.setText("");

            tempText.setText( ""+temp + " ℃");
            tempText.setVisibility(View.VISIBLE);
            tempMax.setText(""+temp_max + " ℃" );
            tempMax.setVisibility(View.VISIBLE);
            tempMin.setText(""+temp_min + " ℃" );
            tempMin.setVisibility(View.VISIBLE);
            tempIcon.setVisibility(View.VISIBLE);

            String main = weatherModel.getWeatherInfo().getMain();
            int id = weatherModel.getWeatherInfo().getId();

            mainText.setText(main);
            mainText.setVisibility(View.VISIBLE);

            Log.i("id", "onChanged: " + id);

            Drawable img = null;

            if (id >= 200 && id <= 232) {
                img = getResources().getDrawable(R.drawable.lightning);
            } else if (id >= 300 && id <= 531) {
                img = getResources().getDrawable(R.drawable.rain);
            } else if (id >= 600 && id <= 622) {
                img = getResources().getDrawable(R.drawable.snow);
            } else if (id == 800) {
                img = getResources().getDrawable(R.drawable.sun);
            } else {
                img = getResources().getDrawable(R.drawable.partclouds);
            }

            weatherIcon.setImageDrawable(img);
            weatherIcon.setVisibility(View.VISIBLE);

        });

        findWeatherButton.setOnClickListener(view -> {
            String cityName = citySearch.getText().toString();
            weatherViewModel.findWeatherByCity(cityName);

        });
    }
}