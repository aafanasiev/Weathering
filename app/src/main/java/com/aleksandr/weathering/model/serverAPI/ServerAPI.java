package com.aleksandr.weathering.model.serverAPI;

import com.aleksandr.weathering.model.allWeather.AllWeather;
import com.aleksandr.weathering.model.currentWeather.CurrentWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServerAPI {

    @GET("weather")
    Call<CurrentWeather> getCurrentWeather(@Query("q") String city, @Query("units") String units, @Query("lang") String lang, @Query("appid") String id);

    @GET("forecast/daily")
    Call<AllWeather> getAllWeather(@Query("q") String city, @Query("lang") String lang, @Query("mode") String mode, @Query("units") String units, @Query("cnt") int cnt, @Query("appid") String id);
}
