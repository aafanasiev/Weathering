package com.aleksandr.weathering.model.allWeather;

/**
 * Created by Aleksandr on 08.06.2016.
 */
public class WeatherMain {

    private String img;
    private int day;
    private String description;
    private double minTemperature;
    private double maxTemperature;

    private double mornTemperature;
    private double nightTemperature;
    private double pressure;
    private int humidity;
    private double wind;
    private int cloud;
    private double rain;

//    public WeatherMain(String img, int day, String description, double minTemperature, double maxTemperature) {
//        this.img = img;
//        this.day = day;
//        this.description = description;
//        this.minTemperature = minTemperature;
//        this.maxTemperature = maxTemperature;
//    }

    public WeatherMain(String img, int day, String description, double minTemperature, double maxTemperature, double mornTemperature, double nightTemperature, double pressure, int humidity, double wind, int cloud, double rain) {
        this.img = img;
        this.day = day;
        this.description = description;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.mornTemperature = mornTemperature;
        this.nightTemperature = nightTemperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.wind = wind;
        this.cloud = cloud;
        this.rain = rain;
    }

    public double getMornTemperature() {
        return mornTemperature;
    }

    public void setMornTemperature(double mornTemperature) {
        this.mornTemperature = mornTemperature;
    }

    public double getNightTemperature() {
        return nightTemperature;
    }

    public void setNightTemperature(double nightTemperature) {
        this.nightTemperature = nightTemperature;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getWind() {
        return wind;
    }

    public void setWind(double wind) {
        this.wind = wind;
    }

    public int getCloud() {
        return cloud;
    }

    public void setCloud(int cloud) {
        this.cloud = cloud;
    }

    public double getRain() {
        return rain;
    }

    public void setRain(double rain) {
        this.rain = rain;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }
}
