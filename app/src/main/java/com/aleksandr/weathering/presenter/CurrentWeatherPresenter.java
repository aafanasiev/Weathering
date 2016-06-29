package com.aleksandr.weathering.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.aleksandr.weathering.WeatheringApp;
import com.aleksandr.weathering.model.currentWeather.CurrentWeather;
import com.aleksandr.weathering.model.currentWeather.WeatherMainCurrent;
import com.aleksandr.weathering.model.serverAPI.ServerAPI;
import com.aleksandr.weathering.presenter.interfaces.CurrentWeatherInterface;
import com.aleksandr.weathering.utils.Constants;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrentWeatherPresenter {

    @Inject
    Context context;

    @Inject
    SharedPreferences preferences;

    @Inject
    ServerAPI serverAPI;

    private CurrentWeatherInterface currentWeatherInterface;

    public CurrentWeatherPresenter(CurrentWeatherInterface currentWeatherInterface) {
        WeatheringApp.dataComponent().inject(this);
        this.currentWeatherInterface = currentWeatherInterface;
    }

    public void createCurrentWeather() {
        serverAPI.getCurrentWeather(preferences.getString("city", "Kyiv"), preferences.getString("units", "metric"), preferences.getString("lang", "ru"), Constants.IDS).enqueue(
                new Callback<CurrentWeather>() {
                    @Override
                    public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {

                        CurrentWeather currentWeather = response.body();
                        WeatherMainCurrent weatherMainCurrent = new WeatherMainCurrent(currentWeather.getWeather().get(0).getDescription(),
                                currentWeather.getMain().getTemp(), currentWeather.getWind().getSpeed(), currentWeather.getMain().getTempMin(), currentWeather.getMain().getTempMax(),
                                currentWeather.getDt(), currentWeather.getMain().getHumidity(), currentWeather.getWeather().get(0).getIcon());
                        currentWeatherInterface.getCurrentWeather(weatherMainCurrent);
                    }

                    @Override
                    public void onFailure(Call<CurrentWeather> call, Throwable t) {
                        Log.e("Error", "Error Current Weather");
                    }
                }
        );
    }
}