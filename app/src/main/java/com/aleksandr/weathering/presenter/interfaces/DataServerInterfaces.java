package com.aleksandr.weathering.presenter.interfaces;

import com.aleksandr.weathering.model.allWeather.WeatherMain;

import java.util.List;

/**
 * Created by Aleksandr on 07.06.2016.
 */
public interface DataServerInterfaces {

    void getData(List<WeatherMain> list);

}
