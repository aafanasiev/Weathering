package com.aleksandr.weathering.model.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "weather.db";

    public DataBaseHelper(Context context) {
        super(context, context.getCacheDir() + File.separator + DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_TABLE = "CREATE TABLE " + Contract.TemperatureEntry.TABLE_NAME + " ("
                + Contract.TemperatureEntry._ID + " INTEGER PRIMARY KEY, "
                + Contract.TemperatureEntry.COLUMN_ICON + " TEXT NOT NULL, "
                + Contract.TemperatureEntry.COLUMN_DATE + " INTEGER NOT NULL, "
                + Contract.TemperatureEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL, "
                + Contract.TemperatureEntry.COLUMN_MIN_TEMP + " DOUBLE NOT NULL, "
                + Contract.TemperatureEntry.COLUMN_MAX_TEMP + " DOUBLE NOT NULL, "
                + Contract.TemperatureEntry.COLUMN_MORNING_TEMP + " DOUBLE NOT NULL, "
                + Contract.TemperatureEntry.COLUMN_NIGHT_TEMP + " DOUBLE NOT NULL, "
                + Contract.TemperatureEntry.COLUMN_PRESSURE + " DOUBLE NOT NULL, "
                + Contract.TemperatureEntry.COLUMN_HUMIDITY + " INTEGER NOT NULL, "
                + Contract.TemperatureEntry.COLUMN_WIND + " DOUBLE NOT NULL, "
                + Contract.TemperatureEntry.COLUMN_CLOUD + " INTEGER NOT NULL, "
                + Contract.TemperatureEntry.COLUMN_RAIN + " DOUBLE NOT NULL " + ");";
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Contract.TemperatureEntry.TABLE_NAME);
        onCreate(db);
    }
}