package com.aleksandr.weathering.presenter.interfaces;

import com.aleksandr.weathering.model.currentWeather.CurrentWeather;
import com.aleksandr.weathering.model.currentWeather.WeatherMainCurrent;

import retrofit2.Response;

/**
 * Created by a.afanasiev on 15.06.2016.
 */
public interface CurrentWeatherInterface {

    void getCurrentWeather(WeatherMainCurrent weatherMainCurrent);

}
