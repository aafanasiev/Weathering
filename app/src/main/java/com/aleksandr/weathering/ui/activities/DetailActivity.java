package com.aleksandr.weathering.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.aleksandr.weathering.R;

/**
 * Created by a.afanasiev on 14.06.2016.
 */
public class DetailActivity extends Activity {

    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        t = (TextView)findViewById(R.id.tv);

        Intent intent = getIntent();
        t.setText(intent.getIntExtra("date", 0) + " " + intent.getDoubleExtra("tempMin", 0) + " " + intent.getDoubleExtra("tempMax", 0)
        + " " + intent.getStringExtra("img"));

    }
}
