package com.aleksandr.weathering.utils;

/**
 * Created by a.afanasiev on 14.06.2016.
 */
public class Utils {

    public static String getDay(int a) {
        String day = "date";
        long b = (long)a;
        if ((System.currentTimeMillis() + 86400000) % ((b * 1000) - 36_000_000) < 86_400_000) {
            day = "Завтра";
            return day;
        } else {
            switch ((a / (60 * 60 * 24)) % 7) {
                case 0:
                    day = "Четверг";
                    return day;
                case 1:
                    day = "Пятница";
                    return day;
                case 2:
                    day = "Суббота";
                    return day;
                case 3:
                    day = "Воскресенье";
                    return day;
                case 4:
                    day = "Понедельник";
                    return day;
                case 5:
                    day = "Вторник";
                    return day;
                case 6:
                    day = "Среда";
                    return day;

            }
            return day;
        }
    }

    public static String getCurrentTemperature(double a){
        String temp = null;

        temp = String.valueOf((int) a / 1) + " " + (char) 0x00B0;

        return temp;
    }

    public static String getTemperature(double a, double b) {
        String temp = null;

        temp = String.valueOf((int) a / 1) + (char) 0x00B0 + " - " + String.valueOf((int) b / 1) + (char) 0x00B0;

        return temp;
    }

}
