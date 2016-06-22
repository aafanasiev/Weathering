package com.aleksandr.weathering.ui.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import javax.inject.Inject;

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

    TextView textView;

    public CurrentWeatherFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        WeatheringApp.dataComponent().inject(this);

        View v = inflater.inflate(R.layout.current_weather, container, false);
        textView = (TextView) v.findViewById(R.id.currentWeather);
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
    public void getCurrentWeather(Response<CurrentWeather> response) {
        CurrentWeather currentWeather = response.body();

        weatherMainCurrent = new WeatherMainCurrent(currentWeather.getWeather().get(0).getDescription(),
                currentWeather.getMain().getTemp(), currentWeather.getWind().getSpeed());

        textView.setText(weatherMainCurrent.getDescription() + " " + weatherMainCurrent.getTemperature() + " " + weatherMainCurrent.getWind());
    }
}