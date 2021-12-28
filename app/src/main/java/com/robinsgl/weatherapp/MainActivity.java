package com.robinsgl.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button findWeatherButton;
    EditText citySearch;
    TextView cityHeader;
    TextView tempText;
    TextView descriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findWeatherButton = findViewById(R.id.search_button);
        citySearch = findViewById(R.id.city_search_edit_text);
        cityHeader = findViewById(R.id.city_header_text_view);
        tempText = findViewById(R.id.temp_text_view);
        descriptionText = findViewById(R.id.description_text_view);

        findWeatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toastMessage = "You searched for " + citySearch.getText().toString();
                Log.i("citySearch", citySearch.getText().toString());
                Toast toast = Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
}