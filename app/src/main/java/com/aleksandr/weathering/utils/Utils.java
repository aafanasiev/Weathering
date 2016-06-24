package com.aleksandr.weathering.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.aleksandr.weathering.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by a.afanasiev on 14.06.2016.
 */
public class Utils {

    Context context;

    public static String getDay(int a, Context context) {
        String day = "date";
        long b = (long)a;
        if ((System.currentTimeMillis() + 86400000) % ((b * 1000) ) < 86_400_000) {
            day = "Завтра";
            return day;
        } else {
            switch (((a) / (60 * 60 * 24)) % 7) {
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

    public static Drawable getImage(String a, Context context){

        Drawable drawable = null;
        ImageView imageView = new ImageView(context.getApplicationContext());
        Bitmap b = null;

        switch (a){
            case "01d":
                drawable = context.getResources().getDrawable(R.drawable.sunny);

                return drawable;
            case "10d":
                drawable = context.getResources().getDrawable(R.drawable.rainny);
                return drawable;
            case "02d":
                drawable = context.getResources().getDrawable(R.drawable.small_rainny);
                return drawable;
            default:
                drawable = context.getResources().getDrawable(R.drawable.sunny);
        }

        return drawable;
    }

    public static String getDate(int a){
        String date = null;
        String dateFormat = "dd/MM/yy";
        long dt = (long)a * 1000;

        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dt);

        date = String.format(format.format(calendar.getTime()));

        return date;
    }

    public static String getCurrentTemperature(double a){
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
