package com.aleksandr.weathering.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.aleksandr.weathering.WeatheringApp;
import com.aleksandr.weathering.model.TimeOutCacheLife;
import com.aleksandr.weathering.model.allWeather.AllWeather;
import com.aleksandr.weathering.model.allWeather.WeatherMain;
import com.aleksandr.weathering.model.serverAPI.ServerAPI;
import com.aleksandr.weathering.presenter.interfaces.DataServerInterfaces;
import com.aleksandr.weathering.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataServerPresenter {

    public static final String TAG = DataServerPresenter.class.getSimpleName();

    @Inject
    Context context;

    @Inject
    SharedPreferences preferences;

    @Inject
    ServerAPI serverAPI;

    private DataServerInterfaces dataServerInterfaces;
    List<com.aleksandr.weathering.model.allWeather.List> list = new ArrayList<>();
    List<WeatherMain> mains;
    TimeOutCacheLife timeOutCacheLife;

    public DataServerPresenter(Context context,DataServerInterfaces dataServerInterfaces) {
        WeatheringApp.dataComponent().inject(this);
        this.dataServerInterfaces = dataServerInterfaces;
    }

    public void createDataWeather() {

        timeOutCacheLife = new TimeOutCacheLife(context);

        mains = timeOutCacheLife.getData();
        if (mains != null && mains.size() > 1){
            Log.e(TAG, "createDataWeather: IN CACHE");
            dataServerInterfaces.getData(mains);
        }

        else {

            Call<AllWeather> allWeatherCall = serverAPI.getAllWeather(preferences.getString("city", "Kyiv"), preferences.getString("lang", "ua"), Constants.MODE, preferences.getString("units", "metric"), Constants.COUNT, Constants.IDS);
            allWeatherCall.enqueue(new Callback<AllWeather>() {
                @Override
                public void onResponse(Call<AllWeather> call, Response<AllWeather> response) {

                    Log.e(TAG, "onResponse: NOT IN CACHE");

                    list = response.body().getList();

                    List<WeatherMain> weatherMains = new ArrayList<>();

                    for (com.aleksandr.weathering.model.allWeather.List all : list) {
                        weatherMains.add(new WeatherMain(
                                all.getWeather().get(0).getIcon(),
                                all.getDt(),
                                all.getWeather().get(0).getDescription(),
                                all.getTemp().getMin(),
                                all.getTemp().getMax()));
                    }

                    dataServerInterfaces.getData(weatherMains);
                    timeOutCacheLife.putData(weatherMains);
                }

                @Override
                public void onFailure(Call<AllWeather> call, Throwable t) {
                    Log.e("ERROR", "Retrofit");
                }
            });
        }
    }


}