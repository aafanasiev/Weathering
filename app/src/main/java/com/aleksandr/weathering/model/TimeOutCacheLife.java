package com.aleksandr.weathering.model;

import android.app.AlarmManager;
import android.content.Context;
import android.database.Cursor;
import android.os.SystemClock;

import com.aleksandr.weathering.model.dataBase.Contract;

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
    }

    private void cleanCache(Context context) {
        if (!isTimeClean(context)) {
//            manager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
//                    SystemClock.elapsedRealtime(), 30000,
//                    WeatherReceiver.makeReceiver(context));
            manager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,SystemClock.elapsedRealtime(),3000,WeatherReceiver.makeReceiver(context));
        }
    }

    private boolean isTimeClean(Context context) {
        return WeatherReceiver.checkReceiver(context) != null;
    }

}