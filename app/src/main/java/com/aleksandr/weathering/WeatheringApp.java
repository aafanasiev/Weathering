package com.aleksandr.weathering;

import android.app.Application;

import com.aleksandr.weathering.di.DaggerDataComponent;
import com.aleksandr.weathering.di.DataComponent;
import com.aleksandr.weathering.di.DataModule;

public class WeatheringApp extends Application {

    private static DataComponent dataComponent;

    public static DataComponent dataComponent() {
        return dataComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initializeDaggerGraph();
    }

    private void initializeDaggerGraph() {
        dataComponent = DaggerDataComponent.builder()
                .dataModule(new DataModule(this))
                .build();
    }
}
