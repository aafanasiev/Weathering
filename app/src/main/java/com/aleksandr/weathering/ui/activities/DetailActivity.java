package com.aleksandr.weathering.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.aleksandr.weathering.R;
import com.aleksandr.weathering.WeatheringApp;
import com.aleksandr.weathering.utils.Utils;

import javax.inject.Inject;

/**
 * Created by a.afanasiev on 14.06.2016.
 */
public class DetailActivity extends Activity {

    @Inject
    SharedPreferences preferences;

    public TextView date;
    public TextView city;
    public TextView detailTemp;
    public TextView tempMorning;
    public TextView tempNight;
    public ImageView detailImage;
    public TextView detailPresure;
    public TextView detailHamidity;
    public TextView detailRain;
    public TextView detailWind;
    public TextView detailCloud;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        WeatheringApp.dataComponent().inject(this);

        city = (TextView) findViewById(R.id.detail_city);
        detailTemp = (TextView) findViewById(R.id.detail_temperature);
        tempMorning = (TextView) findViewById(R.id.detail_morn_temp);
        tempNight = (TextView) findViewById(R.id.detail_night_temp);
        detailImage = (ImageView) findViewById(R.id.detail_image);
        detailPresure = (TextView) findViewById(R.id.detail_presure);
        detailHamidity = (TextView) findViewById(R.id.detail_humidity);
        detailRain = (TextView) findViewById(R.id.detail_rain);
        detailWind = (TextView) findViewById(R.id.detail_wind);
        detailCloud = (TextView) findViewById(R.id.detail_cloud);
        date = (TextView) findViewById(R.id.detail_date);

        Intent intent = getIntent();
//        t.setText(intent.getIntExtra("date", 0) + " " + intent.getDoubleExtra("tempMin", 0) + " " + intent.getDoubleExtra("tempMax", 0)
//        + " " + intent.getStringExtra("img"));

        detailImage.setImageDrawable(Utils.getImage(intent.getStringExtra("img"), this));
        date.setText(Utils.getDate(intent.getIntExtra("date", 0)));
        tempNight.setText(Utils.getCurrentTemperature(intent.getDoubleExtra("tempNight", 0)));
        tempMorning.setText(Utils.getCurrentTemperature(intent.getDoubleExtra("tempMorn", 0)));
        detailTemp.setText(Utils.getCurrentTemperature(intent.getDoubleExtra("tempMax", 0)));
        detailCloud.setText(String.valueOf(intent.getIntExtra("cloud", 0)));
        detailHamidity.setText(String.valueOf(intent.getIntExtra("hamidity", 0)));
        detailWind.setText(String.valueOf(intent.getDoubleExtra("wind", 0)));
        detailRain.setText(String.valueOf(intent.getDoubleExtra("rain", 0)));

        city.setText(preferences.getString("city", "Киев"));


    }
}
