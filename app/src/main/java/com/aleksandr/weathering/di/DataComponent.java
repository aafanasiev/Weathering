package com.aleksandr.weathering.di;

import com.aleksandr.weathering.presenter.CurrentWeatherPresenter;
import com.aleksandr.weathering.presenter.DataServerPresenter;
import com.aleksandr.weathering.ui.activities.MainActivity;
import com.aleksandr.weathering.ui.fragments.CurrentWeatherFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Aleksandr on 06.06.2016.
 */
@Singleton
@Component(modules = DataModule.class)
public interface DataComponent {

    void inject(MainActivity mainActivity);

    void inject(DataServerPresenter dataServerPresenter);

    void inject(CurrentWeatherFragment currentWeatherFragment);

    void inject(CurrentWeatherPresenter currentWeatherPresenter);

}
