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

public class DetailActivity extends Activity {

    @Inject
    SharedPreferences preferences;

    private TextView date;
    private TextView city;
    private TextView detailTemp;
    private TextView tempMorning;
    private TextView tempNight;
    private ImageView detailImage;
    private TextView detailPresure;
    private TextView detailHamidity;
    private TextView detailRain;
    private TextView detailWind;
    private TextView detailCloud;
    private TextView detailDesc;

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
        detailDesc = (TextView)findViewById(R.id.detail_desc);

        Intent intent = getIntent();
        detailImage.setImageDrawable(Utils.getImage(intent.getStringExtra("img"), this));
        date.setText(Utils.getDate(intent.getIntExtra("date", 0)));
        tempNight.setText(Utils.getCurrentTemperature(intent.getDoubleExtra("tempNight", 0)));
        tempMorning.setText(Utils.getCurrentTemperature(intent.getDoubleExtra("tempMorn", 0)));
        detailTemp.setText(Utils.getCurrentTemperature(intent.getDoubleExtra("tempMax", 0)));
        detailCloud.setText(String.valueOf(intent.getIntExtra("cloud", 0) + getString(R.string.percent_unit)));
        detailPresure.setText(String.valueOf(intent.getDoubleExtra("pressure",0)) + getString(R.string.pressure_unit));
        detailHamidity.setText(String.valueOf(intent.getIntExtra("hamidity", 0)) + getString(R.string.percent_unit));
        detailWind.setText(String.valueOf(intent.getDoubleExtra("wind", 0)) + getString(R.string.wind_unit));
        detailRain.setText(String.valueOf(intent.getDoubleExtra("rain", 0)) + getString(R.string.percent_unit));
        detailDesc.setText(intent.getStringExtra("desc"));

        city.setText(preferences.getString("city", getString(R.string.default_city)));
    }
}
