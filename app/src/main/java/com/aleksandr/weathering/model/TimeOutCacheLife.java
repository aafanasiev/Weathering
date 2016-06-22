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

        final String SELECTION_DATA = Contract.TemperatureEntry.COLUMN_DATE + " <= ?";

        String[] selectionArgs = {String.valueOf((System.currentTimeMillis() / 1000))};

        Cursor cursorCache = context.getContentResolver().query(Contract.TemperatureEntry.CONTENT_URI,
                new String[]{Contract.TemperatureEntry.COLUMN_MIN_TEMP}, SELECTION_DATA, selectionArgs, null);

        if (cursorCache != null && cursorCache.moveToFirst()) {
            do {
                String deleteCache = cursorCache.getString(cursorCache.getColumnIndex(Contract.TemperatureEntry.COLUMN_MIN_TEMP));
                context.getContentResolver().delete(Contract.TemperatureEntry.CONTENT_URI, Contract.TemperatureEntry.COLUMN_MIN_TEMP + "=?",
                        new String[]{deleteCache});

            } while (cursorCache.moveToNext());

        }
        Log.e("TAGSS", "removeCache: DELETE CACHE");
    }

    private void cleanCache(Context context) {
        if (!isTimeClean(context)) {
//            manager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
//                    SystemClock.elapsedRealtime(), 30000,
//                    WeatherReceiver.makeReceiver(context));

            manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 3000, WeatherReceiver.makeReceiver(context));
//            manager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,SystemClock.elapsedRealtime(),3000,WeatherReceiver.makeReceiver(context));
        }
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
            context.getContentResolver().insert(Contract.TemperatureEntry.CONTENT_URI, values);
        }
    }

    public List<WeatherMain> getData() {

        List<WeatherMain> wm = new ArrayList<>();
        String[] projection = {Contract.TemperatureEntry.COLUMN_ICON, Contract.TemperatureEntry.COLUMN_DATE, Contract.TemperatureEntry.COLUMN_DESCRIPTION, Contract.TemperatureEntry.COLUMN_MIN_TEMP, Contract.TemperatureEntry.COLUMN_MAX_TEMP};
        Cursor cursor = context.getContentResolver().query(Contract.TemperatureEntry.CONTENT_URI, projection, null, null, null);

        while (cursor.moveToNext()) {
            String icon = cursor.getString(cursor.getColumnIndex(Contract.TemperatureEntry.COLUMN_ICON));
            int date = cursor.getInt(cursor.getColumnIndex(Contract.TemperatureEntry.COLUMN_DATE));
            String description = cursor.getString(cursor.getColumnIndex(Contract.TemperatureEntry.COLUMN_DESCRIPTION));
            double minTemp = cursor.getDouble(cursor.getColumnIndex(Contract.TemperatureEntry.COLUMN_MIN_TEMP));
            double maxTemp = cursor.getDouble(cursor.getColumnIndex(Contract.TemperatureEntry.COLUMN_MAX_TEMP));
            wm.add(new WeatherMain(icon,date,description,minTemp,maxTemp));
        }

        cursor.close();
        return wm;
    }

}