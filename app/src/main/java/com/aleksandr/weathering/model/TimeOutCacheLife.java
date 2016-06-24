package com.aleksandr.weathering.model;

import android.app.AlarmManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.SystemClock;
import android.util.Log;

import com.aleksandr.weathering.model.allWeather.WeatherMain;
import com.aleksandr.weathering.model.dataBase.Contract;
import com.aleksandr.weathering.model.dataBase.DataBaseHelper;
import com.aleksandr.weathering.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class TimeOutCacheLife {

    private Context context;
    private AlarmManager manager;

    public TimeOutCacheLife(Context context) {
        this.context = context;
        manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        cleanCache(context);
    }

    public void removeCache() {

        DataBaseHelper helper = new DataBaseHelper(context);
        SQLiteDatabase database = helper.getWritableDatabase();

        database.delete(Contract.TemperatureEntry.TABLE_NAME, null, null);

//        database.execSQL("DROP TABLE IF EXISTS " + Contract.TemperatureEntry.TABLE_NAME);

//        final String SELECTION_DATA = Contract.TemperatureEntry.COLUMN_DATE + " <= ?";
//
//        String[] selectionArgs = {String.valueOf((System.currentTimeMillis() / 1000))};
//
//        Cursor cursorCache = context.getContentResolver().query(Contract.TemperatureEntry.CONTENT_URI,
//                new String[]{Contract.TemperatureEntry.COLUMN_MIN_TEMP}, SELECTION_DATA, selectionArgs, null);
//
//        if (cursorCache != null && cursorCache.moveToFirst()) {
//            do {
//                String deleteCache = cursorCache.getString(cursorCache.getColumnIndex(Contract.TemperatureEntry.COLUMN_MIN_TEMP));
//                context.getContentResolver().delete(Contract.TemperatureEntry.CONTENT_URI, Contract.TemperatureEntry.COLUMN_MIN_TEMP + "=?",
//                        new String[]{deleteCache});
//
//            } while (cursorCache.moveToNext());
//
//        }
        Log.e("TAGSS", "removeCache: DELETE CACHE");
    }

    private void cleanCache(Context context) {
//        if (!isTimeClean(context)) {
//            manager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
//                    SystemClock.elapsedRealtime(), 3000,
//                    WeatherReceiver.makeReceiver(context));

            manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+ 3*60*60*1000, 3*60*60*1000, WeatherReceiver.makeReceiver(context));
//            manager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), 600000, WeatherReceiver.makeReceiver(context));
//        }
    }

    private boolean isTimeClean(Context context) {
        return WeatherReceiver.checkReceiver(context) != null;
    }

    public void putData(List<WeatherMain> lists) {
        for (WeatherMain weatherMain : lists) {
            ContentValues values = new ContentValues();
            values.put(Contract.TemperatureEntry.COLUMN_ICON, weatherMain.getImg());
            values.put(Contract.TemperatureEntry.COLUMN_DATE, weatherMain.getDay());
            values.put(Contract.TemperatureEntry.COLUMN_DESCRIPTION, weatherMain.getDescription());
            values.put(Contract.TemperatureEntry.COLUMN_MIN_TEMP, weatherMain.getMinTemperature());
            values.put(Contract.TemperatureEntry.COLUMN_MAX_TEMP, weatherMain.getMaxTemperature());
            values.put(Contract.TemperatureEntry.COLUMN_MORNING_TEMP, weatherMain.getMornTemperature());
            values.put(Contract.TemperatureEntry.COLUMN_NIGHT_TEMP, weatherMain.getNightTemperature());
            values.put(Contract.TemperatureEntry.COLUMN_PRESSURE, weatherMain.getPressure());
            values.put(Contract.TemperatureEntry.COLUMN_HUMIDITY, weatherMain.getHumidity());
            values.put(Contract.TemperatureEntry.COLUMN_WIND,weatherMain.getWind());
            values.put(Contract.TemperatureEntry.COLUMN_CLOUD,weatherMain.getCloud());
            values.put(Contract.TemperatureEntry.COLUMN_RAIN,weatherMain.getRain());
            context.getContentResolver().insert(Contract.TemperatureEntry.CONTENT_URI, values);
        }
    }

    public List<WeatherMain> getData() {
        List<WeatherMain> wm = new ArrayList<>();
        String[] projection = {Contract.TemperatureEntry.COLUMN_ICON, Contract.TemperatureEntry.COLUMN_DATE, Contract.TemperatureEntry.COLUMN_DESCRIPTION, Contract.TemperatureEntry.COLUMN_MIN_TEMP,
                Contract.TemperatureEntry.COLUMN_MAX_TEMP, Contract.TemperatureEntry.COLUMN_MORNING_TEMP,Contract.TemperatureEntry.COLUMN_NIGHT_TEMP, Contract.TemperatureEntry.COLUMN_PRESSURE,
        Contract.TemperatureEntry.COLUMN_HUMIDITY, Contract.TemperatureEntry.COLUMN_WIND, Contract.TemperatureEntry.COLUMN_CLOUD,Contract.TemperatureEntry.COLUMN_RAIN};
        Cursor cursor = context.getContentResolver().query(Contract.TemperatureEntry.CONTENT_URI, projection, null, null, null);
        while (cursor.moveToNext()) {
            String icon = cursor.getString(cursor.getColumnIndex(Contract.TemperatureEntry.COLUMN_ICON));
            int date = cursor.getInt(cursor.getColumnIndex(Contract.TemperatureEntry.COLUMN_DATE));
            String description = cursor.getString(cursor.getColumnIndex(Contract.TemperatureEntry.COLUMN_DESCRIPTION));
            double minTemp = cursor.getDouble(cursor.getColumnIndex(Contract.TemperatureEntry.COLUMN_MIN_TEMP));
            double maxTemp = cursor.getDouble(cursor.getColumnIndex(Contract.TemperatureEntry.COLUMN_MAX_TEMP));
            double mornTemp = cursor.getDouble(cursor.getColumnIndex(Contract.TemperatureEntry.COLUMN_MORNING_TEMP));
            double nightTemp = cursor.getDouble(cursor.getColumnIndex(Contract.TemperatureEntry.COLUMN_NIGHT_TEMP));
            double pressure = cursor.getDouble(cursor.getColumnIndex(Contract.TemperatureEntry.COLUMN_PRESSURE));
            int humidity = cursor.getInt(cursor.getColumnIndex(Contract.TemperatureEntry.COLUMN_HUMIDITY));
            double wind = cursor.getDouble(cursor.getColumnIndex(Contract.TemperatureEntry.COLUMN_WIND));
            int cloud = cursor.getInt(cursor.getColumnIndex(Contract.TemperatureEntry.COLUMN_CLOUD));
            double rain = cursor.getDouble(cursor.getColumnIndex(Contract.TemperatureEntry.COLUMN_RAIN));

            wm.add(new WeatherMain(icon,date,description,minTemp,maxTemp,mornTemp,nightTemp,pressure,humidity,wind,cloud,rain));
        }
        cursor.close();
        return wm;
    }
}