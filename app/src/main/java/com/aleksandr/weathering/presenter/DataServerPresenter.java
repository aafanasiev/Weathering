package com.aleksandr.weathering.presenter;

import android.app.AlarmManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.SystemClock;
import android.util.Log;

import com.aleksandr.weathering.WeatheringApp;
import com.aleksandr.weathering.model.TimeOutCacheLife;
import com.aleksandr.weathering.model.WeatherReceiver;
import com.aleksandr.weathering.model.allWeather.AllWeather;
import com.aleksandr.weathering.model.allWeather.Temp;
import com.aleksandr.weathering.model.allWeather.Weather;
import com.aleksandr.weathering.model.allWeather.WeatherMain;
import com.aleksandr.weathering.model.currentWeather.Sys;
import com.aleksandr.weathering.model.dataBase.Contract;
import com.aleksandr.weathering.model.serverAPI.ServerAPI;
import com.aleksandr.weathering.presenter.interfaces.DataServerInterfaces;
import com.aleksandr.weathering.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Aleksandr on 07.06.2016.
 */
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

//    public DataServerPresenter(Context context) {
//        this.context = context;
//    }

    public void createDataWeather() {

        timeOutCacheLife = new TimeOutCacheLife(context);

        mains = getData();
        if (mains != null && mains.size() > 1){
            Log.e(TAG, "createDataWeather: IN CACHE");
            dataServerInterfaces.getData(mains);
        }

        else {

            Call<AllWeather> allWeatherCall = serverAPI.getAllWeather(preferences.getString("city", "Kyiv"), preferences.getString("lang", "ua"), Constants.MODE, preferences.getString("units", "metric"), Constants.COUNT, Constants.IDS);
            allWeatherCall.enqueue(new Callback<AllWeather>() {
                @Override
                public void onResponse(Call<AllWeather> call, Response<AllWeather> response) {

                    Log.e(TAG, "onResponse: IN CACHE");

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
                    putData(weatherMains);
                }

                @Override
                public void onFailure(Call<AllWeather> call, Throwable t) {
                    Log.e("ERROR", "Retrofit");
                }
            });
        }
    }

    public void putData(List<WeatherMain> lists) {
        for (WeatherMain weatherMain : lists) {
            ContentValues values = new ContentValues();
            values.put(Contract.TemperatureEntry.COLUMN_ICON, weatherMain.getImg());
            values.put(Contract.TemperatureEntry.COLUMN_DATE, weatherMain.getDay());
            values.put(Contract.TemperatureEntry.COLUMN_DESCRIPTION, weatherMain.getDescription());
            values.put(Contract.TemperatureEntry.COLUMN_MIN_TEMP, weatherMain.getMinTemperature());
            values.put(Contract.TemperatureEntry.COLUMN_MAX_TEMP, weatherMain.getMaxTemperature());
            context.getContentResolver().insert(Contract.TemperatureEntry.CONTENT_URI, values);
        }
    }

    public List<WeatherMain> getData() {

        List<WeatherMain> wm = null;
        String[] projection = {Contract.TemperatureEntry.COLUMN_ICON, Contract.TemperatureEntry.COLUMN_DATE, Contract.TemperatureEntry.COLUMN_DESCRIPTION, Contract.TemperatureEntry.COLUMN_MIN_TEMP, Contract.TemperatureEntry.COLUMN_MAX_TEMP};
        Cursor cursor = context.getContentResolver().query(Contract.TemperatureEntry.CONTENT_URI, projection, null, null, null);

        while (cursor.moveToNext()) {
            String icon = cursor.getString(cursor.getColumnIndex(Contract.TemperatureEntry.COLUMN_ICON));
            int date = cursor.getInt(cursor.getColumnIndex(Contract.TemperatureEntry.COLUMN_DATE));
            String description = cursor.getString(cursor.getColumnIndex(Contract.TemperatureEntry.COLUMN_DESCRIPTION));
            double minTemp = cursor.getDouble(cursor.getColumnIndex(Contract.TemperatureEntry.COLUMN_MIN_TEMP));
            double maxTemp = cursor.getDouble(cursor.getColumnIndex(Contract.TemperatureEntry.COLUMN_MAX_TEMP));
            wm.add(new WeatherMain(icon,date,description,minTemp,maxTemp));
        }

        cursor.close();
        return wm;
    }
}