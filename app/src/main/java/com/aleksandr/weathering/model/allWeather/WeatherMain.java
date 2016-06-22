package com.aleksandr.weathering.model.allWeather;

/**
 * Created by Aleksandr on 08.06.2016.
 */
public class WeatherMain {

    private String img;
//    private double temperature;
    private int day;
    private String description;
    private double minTemperature;
    private double maxTemperature;

    public WeatherMain(String img, int day, String description, double minTemperature, double maxTemperature) {
        this.img = img;
        this.day = day;
        this.description = description;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
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

    //    public WeatherMain(double temperature, String description) {
//        this.temperature = temperature;
//        this.description = description;
//    }
//
//    public double getTemperature() {
//        return temperature;
//    }
//
//    public void setTemperature(double temperature) {
//        this.temperature = temperature;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
}
