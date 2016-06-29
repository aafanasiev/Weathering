package com.aleksandr.weathering.model;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class WeatherReceiver extends BroadcastReceiver {

    public static final int DELETE_CACHE_CODE = 100;

    @Override
    public void onReceive(Context context, Intent intent) {
        new TimeOutCacheLife(context).removeCache();
    }

    public static PendingIntent makeReceiver(Context context) {
        return PendingIntent.getBroadcast(context, DELETE_CACHE_CODE, new Intent(context, WeatherReceiver.class), PendingIntent.FLAG_CANCEL_CURRENT);
    }
}
