package com.aleksandr.weathering.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.aleksandr.weathering.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utils {

    public static String getDay(int a, Context context) {
        String day = "";
        long b = (long) a;
        if ((System.currentTimeMillis() + Constants.SECONDS_IN_DAY * Constants.TIME_IN_MILLISECONDS) %
                ((b * Constants.TIME_IN_MILLISECONDS)) < Constants.SECONDS_IN_DAY * Constants.TIME_IN_MILLISECONDS) {
            day = context.getString(R.string.tomorrow);
            return day;
        } else {
            switch (((a) / (Constants.SECONDS_IN_DAY)) % 7) {
                case 0:
                    day = context.getString(R.string.thursday);
                    return day;
                case 1:
                    day = context.getString(R.string.friday);
                    return day;
                case 2:
                    day = context.getString(R.string.saturday);
                    return day;
                case 3:
                    day = context.getString(R.string.sunday);
                    return day;
                case 4:
                    day = context.getString(R.string.monday);
                    return day;
                case 5:
                    day = context.getString(R.string.tuesday);
                    return day;
                case 6:
                    day = context.getString(R.string.wednesday);
                    return day;
            }
            return day;
        }
    }

    public static Drawable getImage(String a, Context context) {

        Drawable drawable = null;

        switch (a) {
            case "01d":
                drawable = context.getResources().getDrawable(R.drawable.sunny);
                return drawable;
            case "02d":
                drawable = context.getResources().getDrawable(R.drawable.some_cloud);
                return drawable;
            case "03d":
                drawable = context.getResources().getDrawable(R.drawable.hadr_cloud);
                return drawable;
            case "04d":
                drawable = context.getResources().getDrawable(R.drawable.hadr_cloud);
                return drawable;
            case "09d":
                drawable = context.getResources().getDrawable(R.drawable.rainny);
                return drawable;
            case "10d":
                drawable = context.getResources().getDrawable(R.drawable.small_rainny);
                return drawable;
            case "11d":
                drawable = context.getResources().getDrawable(R.drawable.thunderstorm);
                return drawable;
            case "13d":
                drawable = context.getResources().getDrawable(R.drawable.snow);
                return drawable;
            case "50d":
                drawable = context.getResources().getDrawable(R.drawable.mist);
                return drawable;
            case "01n":
                drawable = context.getResources().getDrawable(R.drawable.night_clear);
                return drawable;
            case "02n":
                drawable = context.getResources().getDrawable(R.drawable.some_night_cloud);
                return drawable;
            default:
                drawable = context.getResources().getDrawable(R.drawable.sunny);
        }
        return drawable;
    }

    public static String getDate(int a) {
        String date = null;
        String dateFormat = "dd/MM/yy";
        long dt = (long) a * Constants.TIME_IN_MILLISECONDS;

        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dt);

        date = String.format(format.format(calendar.getTime()));

        return date;
    }

    public static String getCurrentTemperature(double a) {
        String temp = null;

        temp = String.valueOf((int) a / 1) + (char) 0x00B0;

        return temp;
    }

    public static String getTemperature(double a, double b) {
        String temp = null;

        temp = String.valueOf((int) a / 1) + (char) 0x00B0 + " - " + String.valueOf((int) b / 1) + (char) 0x00B0;

        return temp;
    }

}
