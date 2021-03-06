package com.aleksandr.weathering.model.dataBase;

import android.net.Uri;
import android.provider.BaseColumns;

public class Contract {

    public static final String CONTENT_AUTHORITY = "com.aleksandr.weathering.model.dataBase";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_TEMPERATURE = TemperatureEntry.TABLE_NAME;

    public static final class TemperatureEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_TEMPERATURE).build();

        public static final String CONTENT_ITEMS_TYPE = "vnd.android.cursor.dir/" + CONTENT_AUTHORITY + "/" + PATH_TEMPERATURE;
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/" + CONTENT_AUTHORITY + "/" + PATH_TEMPERATURE;

        public static final String TABLE_NAME = "all_weather_table";

        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_ICON = "icon";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_MIN_TEMP = "min_temp";
        public static final String COLUMN_MAX_TEMP = "max_temp";
        public static final String COLUMN_MORNING_TEMP = "morning_temp";
        public static final String COLUMN_NIGHT_TEMP = "night_temp";
        public static final String COLUMN_PRESSURE = "pressure";
        public static final String COLUMN_HUMIDITY = "humidity";
        public static final String COLUMN_WIND = "wind";
        public static final String COLUMN_CLOUD = "cloud";
        public static final String COLUMN_RAIN = "rain";
    }
}












