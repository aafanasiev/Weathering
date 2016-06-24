package com.aleksandr.weathering.model.currentWeather;

/**
 * Created by a.afanasiev on 14.06.2016.
 */
public class WeatherMainCurrent {

    private String description;
    private double temperature;
    private double wind;
    private double minCurTemp;
    private double maxCurTemp;
    private int date;
    private int humidity;
    private String image;


    public WeatherMainCurrent(String description, double temperature, double wind, double minCurTemp, double maxCurTemp, int date, int humidity, String image) {
        this.description = description;
        this.temperature = temperature;
        this.wind = wind;
        this.minCurTemp = minCurTemp;
        this.maxCurTemp = maxCurTemp;
        this.date = date;
        this.humidity = humidity;
        this.image = image;

    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getWind() {
        return wind;
    }

    public void setWind(double wind) {
        this.wind = wind;
    }

    public double getMinCurTemp() {
        return minCurTemp;
    }

    public void setMinCurTemp(double minCurTemp) {
        this.minCurTemp = minCurTemp;
    }

    public double getMaxCurTemp() {
        return maxCurTemp;
    }

    public void setMaxCurTemp(double maxCurTemp) {
        this.maxCurTemp = maxCurTemp;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
