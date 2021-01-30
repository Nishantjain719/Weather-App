package com.example.weatherapiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    Button btn_cityId, btn_getWeatherById, btn_getWeatherByName;
    EditText et_datainput;
    ListView lv_weatherReports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_cityId = findViewById(R.id.btn_getCityId);
        btn_getWeatherById = findViewById(R.id.btn_getWeatherByCityId);
        btn_getWeatherByName = findViewById(R.id.btn_getWeatherByCityName);

        et_datainput = findViewById(R.id.et_datainput);
        lv_weatherReports = findViewById(R.id.lv_weatherReports);

       final WeatherDataService weatherDataService = new WeatherDataService(MainActivity.this);

        btn_getWeatherById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weatherDataService.getCityForecastById(et_datainput.getText().toString(), new WeatherDataService.ForecastByIdResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "something wrong", Toast.LENGTH_SHORT).show();
                        
                    }

                    @Override
                    public void onResponse(List<WeatherReportModel> weatherReportModels) {
                       // Toast.makeText(MainActivity.this, weatherReportModel.toString(), Toast.LENGTH_SHORT).show();
                        //put entire list into the listview control

                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, weatherReportModels);
                        lv_weatherReports.setAdapter(arrayAdapter);


                    }
                });

            }
        });

        btn_getWeatherByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weatherDataService.getCityForecastByName(et_datainput.getText().toString(), new WeatherDataService.GetCityForecastByNameCallback() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "something wrong", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(List<WeatherReportModel> weatherReportModels) {
                        // Toast.makeText(MainActivity.this, weatherReportModel.toString(), Toast.LENGTH_SHORT).show();
                        //put entire list into the listview control

                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, weatherReportModels);
                        lv_weatherReports.setAdapter(arrayAdapter);


                    }
                });


            }
        });

        btn_cityId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // sending context to mainActivity AND MOST IMPORTANT IF YOU WANT TO GET THE VALUE OF CITYID, YOU CAN'T JUST RETURN IT AS A STRING WE HAVE TO WAIT FOR IT SO WE USE A CALLBACK METHOD.

                //this didn't return anything
                weatherDataService.getCityId(et_datainput.getText().toString(), new WeatherDataService.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "Something wrong", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(String cityID) {
                        Toast.makeText(MainActivity.this, "Returned an ID of " + cityID, Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });


    }
}