package com.aleksandr.weathering.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.aleksandr.weathering.R;
import com.aleksandr.weathering.WeatheringApp;
import com.aleksandr.weathering.model.allWeather.AllWeather;
import com.aleksandr.weathering.model.allWeather.WeatherMain;
import com.aleksandr.weathering.model.currentWeather.WeatherMainCurrent;
import com.aleksandr.weathering.model.serverAPI.ServerAPI;
import com.aleksandr.weathering.presenter.CurrentWeatherPresenter;
import com.aleksandr.weathering.presenter.DataServerPresenter;
import com.aleksandr.weathering.presenter.interfaces.CurrentWeatherInterface;
import com.aleksandr.weathering.presenter.interfaces.DataServerInterfaces;
import com.aleksandr.weathering.ui.adapters.WeatherAdapter;
import com.aleksandr.weathering.ui.fragments.CurrentWeatherFragment;
import com.aleksandr.weathering.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;

/**
 * Created by Aleksandr on 06.06.2016.
 */
public class MainActivity extends AppCompatActivity implements DataServerInterfaces,CurrentWeatherInterface {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager manager;
    CurrentWeatherFragment currentWeatherFragment;
    List<WeatherMain> mainList = new ArrayList<>();
    DataServerPresenter dataServerPresenter;
    CurrentWeatherPresenter currentWeatherPresenter;
    AppBarLayout appBarLayout;
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;


    @Inject
    SharedPreferences preferences;

    @Inject
    Context context;

    @Inject
    ServerAPI serverAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WeatheringApp.dataComponent().inject(this);
        init();

        dataServerPresenter.createDataWeather();
        currentWeatherPresenter.createCurrentWeather();
    }

    public void init() {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        currentWeatherFragment = new CurrentWeatherFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, currentWeatherFragment, "Detail").commit();

        recyclerView = (RecyclerView) findViewById(R.id.rview);
        manager = new LinearLayoutManager(context);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        adapter = new WeatherAdapter(mainList, context);
        recyclerView.setAdapter(adapter);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        collapsingToolbarLayout.setTitle(" ");
        setSupportActionBar(toolbar);
        appBarLayout = (AppBarLayout)findViewById(R.id.app_bar);
        dataServerPresenter = new DataServerPresenter(this,this);
        currentWeatherPresenter = new CurrentWeatherPresenter(this);
    }

    @Override
    protected void onResume() {

//        String a = mainList.get(0).getImg();
//        TextView textView = new TextView(this);
//        textView.setText(a);


        super.onResume();


    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuItem menuItem = menu.add(0, 1, 0, "Preferences");
//        menuItem.setIntent(new Intent(this, PrefActivity.class));
//        return super.onCreateOptionsMenu(menu);
//    }

    @Override
    public void getData(List<WeatherMain> list) {

        for (int i = 1; i < list.size(); i++) {
            mainList.add(new WeatherMain(list.get(i).getImg(), list.get(i).getDay(), list.get(i).getDescription(), list.get(i).getMinTemperature(), list.get(i).getMaxTemperature(),
                    list.get(i).getMornTemperature(),list.get(i).getNightTemperature(),list.get(i).getPressure(),list.get(i).getHumidity(),list.get(i).getWind(),list.get(i).getCloud(),
                    list.get(i).getRain()));
        }




    }

    @Override
    public void getCurrentWeather(final WeatherMainCurrent weatherMainCurrent) {
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1){
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0){
                    collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
                    collapsingToolbarLayout.setExpandedTitleMarginStart(30);
                    collapsingToolbarLayout.setTitle(preferences.getString("city", "Киев") + ", " + Utils.getCurrentTemperature(weatherMainCurrent.getTemperature()) + " " + weatherMainCurrent.getDescription());
                    isShow = true;
                    toolbar.setTitle("");
                } else if (isShow){
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                    toolbar.setTitle("");
                }
            }
        });
    }
}