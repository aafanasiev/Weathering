package com.aleksandr.weathering.ui.activities;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.aleksandr.weathering.R;

public class PrefActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference);
    }
}
