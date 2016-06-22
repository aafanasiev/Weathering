package com.aleksandr.weathering.ui.activities;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.aleksandr.weathering.R;

/**
 * Created by Aleksandr on 09.06.2016.
 */
public class PrefActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference);
    }
}
