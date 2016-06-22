package com.aleksandr.weathering.model.dataBase;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by a.afanasiev on 17.06.2016.
 */
public class Providers extends ContentProvider {

    private static final String TAG = Providers.class.getSimpleName();
    private DataBaseHelper helper;

    private static final int TEMPS = 100;
    private static final int TEMP = 101;

    private static final UriMatcher uriMatcher = buildUriMatcher();

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

        matcher.addURI(Contract.CONTENT_AUTHORITY, Contract.PATH_TEMPERATURE, TEMPS);
        matcher.addURI(Contract.CONTENT_AUTHORITY, Contract.PATH_TEMPERATURE + "/#", TEMP);
        return matcher;
    }


    @Override
    public boolean onCreate() {
        helper = new DataBaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor;
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(Contract.TemperatureEntry.TABLE_NAME);

        switch (uriMatcher.match(uri)) {
            case TEMPS:
                cursor = queryBuilder.query(helper.getWritableDatabase(), projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case TEMP:
                final String rowID = "" + Contract.TemperatureEntry._ID + " ='" + ContentUris.parseId(uri) + "'";
                cursor = queryBuilder.query(helper.getWritableDatabase(), projection, rowID, null, null, null, sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int match = uriMatcher.match(uri);

        switch (match) {
            case TEMP:
                return Contract.TemperatureEntry.CONTENT_ITEM_TYPE;
            case TEMPS:
                return Contract.TemperatureEntry.CONTENT_ITEMS_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase database = helper.getWritableDatabase();
        Uri resultUri;

        switch (uriMatcher.match(uri)) {
            case TEMPS:
                long id = database.insert(Contract.PATH_TEMPERATURE, "", values);

                if (id > 0)
                    resultUri = ContentUris.withAppendedId(Contract.TemperatureEntry.CONTENT_URI, id);
                else
                    throw new android.database.SQLException("Fail" + uri);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return resultUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase database = helper.getWritableDatabase();

        int rowDelete = 0;
        switch (uriMatcher.match(uri)) {
            case TEMPS:
                rowDelete = database.delete(Contract.TemperatureEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (selection == null || rowDelete != 0)
            getContext().getContentResolver().notifyChange(uri, null);

        return rowDelete;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase database = helper.getWritableDatabase();
        int rowUpdate;

        switch (uriMatcher.match(uri)) {
            case TEMPS:
                rowUpdate = database.update(Contract.TemperatureEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (rowUpdate != 0)
            getContext().getContentResolver().notifyChange(uri,null);

        return rowUpdate;
    }
}
