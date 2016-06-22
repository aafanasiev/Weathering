package com.aleksandr.weathering.model.currentWeather;

/**
 * Created by a.afanasiev on 14.06.2016.
 */
public class WeatherMainCurrent {

    private String description;
    private double temperature;
    private double wind;

    public WeatherMainCurrent(String description, double temperature, double wind) {
        this.description = description;
        this.temperature = temperature;
        this.wind = wind;
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
}
