package com.aleksandr.weathering.ui.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aleksandr.weathering.R;
import com.aleksandr.weathering.WeatheringApp;
import com.aleksandr.weathering.model.currentWeather.CurrentWeather;
import com.aleksandr.weathering.model.currentWeather.WeatherMainCurrent;
import com.aleksandr.weathering.model.serverAPI.ServerAPI;
import com.aleksandr.weathering.presenter.CurrentWeatherPresenter;
import com.aleksandr.weathering.presenter.interfaces.CurrentWeatherInterface;
import com.aleksandr.weathering.ui.activities.MainActivity;
import com.aleksandr.weathering.utils.Constants;
import com.aleksandr.weathering.utils.Utils;

import javax.inject.Inject;

import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by a.afanasiev on 14.06.2016.
 */
public class CurrentWeatherFragment extends Fragment implements CurrentWeatherInterface {

    @Inject
    Context context;

    @Inject
    ServerAPI serverAPI;

//    @Inject
//    SharedPreferences preferences;

    SharedPreferences preferences;

    CurrentWeatherPresenter currentWeatherPresenter;

    WeatherMainCurrent weatherMainCurrent;

    TextView deltaTemp;
    TextView curTemp;
    TextView curWind;
    TextView curDesc;
    TextView curCity;
    TextView curDate;
    TextView curDay;
    TextView curHumidity;
    ImageView curImage;

    public CurrentWeatherFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        WeatheringApp.dataComponent().inject(this);

        View v = inflater.inflate(R.layout.current_weather, container, false);
//        deltaTemp = (TextView) v.findViewById(R.id.delta_temp);
        curTemp = (TextView) v.findViewById(R.id.text_temp);
        curCity = (TextView) v.findViewById(R.id.city);
        curDate = (TextView) v.findViewById(R.id.date);
        curDay = (TextView) v.findViewById(R.id.day);
        curWind = (TextView) v.findViewById(R.id.wind);
        curHumidity = (TextView) v.findViewById(R.id.humidity);
        curDesc = (TextView) v.findViewById(R.id.temp_desc);
        curImage = (ImageView) v.findViewById(R.id.weather_image);
//        preferences = PreferenceManager.getDefaultSharedPreferences(context);

        preferences = getActivity().getSharedPreferences(Constants.SETTINGS, Context.MODE_PRIVATE);

        currentWeatherPresenter = new CurrentWeatherPresenter(this);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        currentWeatherPresenter.createCurrentWeather();
    }

    @Override
    public void getCurrentWeather(WeatherMainCurrent weatherMainCurrent) {

        curTemp.setText(Utils.getCurrentTemperature(weatherMainCurrent.getTemperature()));
        curDesc.setText(weatherMainCurrent.getDescription());
        curWind.setText("Ветер: " + String.valueOf(weatherMainCurrent.getWind()) + "м/с");
        curHumidity.setText("Влажность: "+ String.valueOf(weatherMainCurrent.getHumidity()) + "%");
        curDate.setText(Utils.getDate(weatherMainCurrent.getDate()));
        curDay.setText(Utils.getDay(weatherMainCurrent.getDate(), getContext()));
        Utils.getImage(weatherMainCurrent.getImage(), context);

        curImage.setImageDrawable(Utils.getImage(weatherMainCurrent.getImage(),context));

//        textView.setText(weatherMainCurrent.getDescription() + " " + weatherMainCurrent.getTemperature() + " " + weatherMainCurrent.getWind());
    }
}