package com.aleksandr.weathering.model.serverAPI;

import com.aleksandr.weathering.model.allWeather.AllWeather;
import com.aleksandr.weathering.model.currentWeather.CurrentWeather;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Aleksandr on 07.06.2016.
 */
public interface ServerAPI {

//    @GET("weather?q={city},ua&units={units}&lang={lang}&appid=efcd21a8f54d97e56e01daa36cd6a4ce")
//    Call<CurrentWeather> getCurrentWeather(@Path("city") String city, @Path("units") String units,@Path("lang") String lang);

    @GET("weather")
    Call<CurrentWeather> getCurrentWeather(@Query("q") String city, @Query("units") String units, @Query("lang") String lang, @Query("appid") String id);

    @GET("forecast/daily")
    Call <AllWeather> getAllWeather(@Query("q") String city, @Query("lang") String lang, @Query("mode") String mode, @Query("units") String units, @Query("cnt") int cnt,  @Query("appid") String id);

//    https://your.api.com/tasks?sort=value-of-order-parameter
//
//    @GET("/users?filters[0][field]={param}&filters[0][operator]=equals&filters[0][value]={value}")
//    UserDto retrieveUsersByFilters(@Path("param") String nameFilter, @Path("value") String value);
//
//    @GET("/users?filters[0][operator]=equals")
//    UserDto retrieveUsersByFilters(@Query("filters[0][field]") String nameFilter,@Query("filters[0][value]") String value);

}
