package com.aleksandr.weathering.di;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.SyncStateContract;
import android.text.format.DateUtils;

import com.aleksandr.weathering.model.serverAPI.ServerAPI;
import com.aleksandr.weathering.utils.Constants;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Aleksandr on 06.06.2016.
 */
@Module
public class DataModule {

    protected final Context context;

    public DataModule(Context context) {
        this.context = context;
    }

    protected SharedPreferences getSharedPreferences(){
        return context.getSharedPreferences(Constants.SETTINGS,Context.MODE_PRIVATE);
    }

    protected OkHttpClient getOkHttpClient(){
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
                builder.readTimeout(15, TimeUnit.SECONDS);
                builder.connectTimeout(10,TimeUnit.SECONDS);
                builder.writeTimeout(10,TimeUnit.SECONDS);
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(context.getCacheDir(),cacheSize);
        builder.cache(cache);
        OkHttpClient client = builder.build();
        return client;
    }

    protected ServerAPI getServerAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build();
        return retrofit.create(ServerAPI.class);
    }

    @Provides
    @Singleton
    public Context provideApplicationContext(){
        return context;
    }

    @Provides
    @Singleton
    public SharedPreferences provideApplicationSharedPreferences(){
        return getSharedPreferences();
    }

    @Provides
    @Singleton
    public ServerAPI provideServerAPI(){
        return getServerAPI();
    }



}
