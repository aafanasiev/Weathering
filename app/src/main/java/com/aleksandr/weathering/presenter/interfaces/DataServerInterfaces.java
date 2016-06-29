package com.aleksandr.weathering.presenter.interfaces;

import com.aleksandr.weathering.model.allWeather.WeatherMain;

import java.util.List;

public interface DataServerInterfaces {
    void getData(List<WeatherMain> list);
}
