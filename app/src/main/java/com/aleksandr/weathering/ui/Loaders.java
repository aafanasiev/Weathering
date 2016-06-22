package com.aleksandr.weathering.ui;

import android.content.AsyncTaskLoader;
import android.content.Context;

/**
 * Created by Aleksandr on 07.06.2016.
 */
public class Loaders extends AsyncTaskLoader {

    public Loaders(Context context) {
        super(context);
    }

    @Override
    public Object loadInBackground() {
        return null;
    }
}
