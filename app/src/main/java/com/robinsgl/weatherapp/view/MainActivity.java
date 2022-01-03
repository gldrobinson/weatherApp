package com.robinsgl.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.robinsgl.weatherapp.viewModel.WeatherViewModel;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    WeatherViewModel weatherViewModel;

    Button findWeatherButton;
    EditText citySearch;
    TextView tempText;
    TextView descriptionText;
    ImageView weatherIcon;
    TextView searchedCity;
    TextView dateText;
    TextView timeText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findWeatherButton = findViewById(R.id.search_button);
        citySearch = findViewById(R.id.city_search_edit_text);
        tempText = findViewById(R.id.temp_text_view);
        descriptionText = findViewById(R.id.description_text_view);
        weatherIcon = findViewById(R.id.weather_icon);
        searchedCity = findViewById(R.id.city_searched_text_view);

        weatherViewModel = new WeatherViewModel();

        weatherViewModel.weatherData.observe(this, weatherModel -> {
            int temp = (int) ( weatherModel.getWeatherMain().getTemp() - 272.15 );

            searchedCity.setText(citySearch.getText().toString());
            searchedCity.setVisibility(View.VISIBLE);
            citySearch.setText("");

            tempText.setText( ""+temp + "℃");
            tempText.setVisibility(View.VISIBLE);

            String description = weatherModel.getWeatherInfo().getDescription();

            descriptionText.setText(description);
            descriptionText.setVisibility(View.VISIBLE);

            int id = weatherModel.getWeatherInfo().getId();
            setWeatherIcon(id);

        });

        findWeatherButton.setOnClickListener(view -> {
            String cityName = citySearch.getText().toString();
            weatherViewModel.findWeatherByCity(cityName);

            // hide keyboard
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);

        });

    }

    public void setWeatherIcon(int id) {
        Drawable img = null;

        if (id >= 200 && id <= 232) {
            img = getResources().getDrawable(R.drawable.thunderstorm);
        } else if ((id >= 300 && id <= 311) || id == 500) {
            img = getResources().getDrawable(R.drawable.lightrain);
        } else if (id == 501 || id == 520 || id == 521) {
            img = getResources().getDrawable(R.drawable.moderaterain);
        } else if (id <= 531)  {
            img = getResources().getDrawable(R.drawable.heavyrain);
        } else if (id == 601 || id == 602 || id == 622) {
            img = getResources().getDrawable(R.drawable.snow);
        } else if (id <= 621) {
            img = getResources().getDrawable(R.drawable.lightsnow);
        } else if (id >= 700 && id <= 781) {
            img = getResources().getDrawable(R.drawable.wind);
        } else if (id == 800) {
            img = getResources().getDrawable(R.drawable.sun);
        } else if (id == 801 || id == 802) {
            img = getResources().getDrawable(R.drawable.lightcloud);
        } else {
            img = getResources().getDrawable(R.drawable.heavycloud);
        }

        weatherIcon.setImageDrawable(img);
        weatherIcon.setVisibility(View.VISIBLE);
    }
}