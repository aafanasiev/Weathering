package com.aleksandr.weathering.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aleksandr.weathering.R;
import com.aleksandr.weathering.model.allWeather.WeatherMain;
import com.aleksandr.weathering.ui.activities.DetailActivity;
import com.aleksandr.weathering.ui.activities.MainActivity;
import com.aleksandr.weathering.ui.viewHolders.WeatherHolder;
import com.aleksandr.weathering.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a.afanasiev on 15.06.2016.
 */
public class WeatherAdapter extends RecyclerView.Adapter<WeatherHolder> {


    Context context;
    List<WeatherMain> mainList = new ArrayList<>();

    public WeatherAdapter(List<WeatherMain> mainList, Context context) {
        this.mainList = mainList;
        this.context = context;
    }

    @Override
    public WeatherHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new WeatherHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherHolder holder, final int position) {
        final WeatherMain main = mainList.get(position);
        holder.img.setImageResource(R.mipmap.ic_launcher);
        holder.date.setText(Utils.getDay(main.getDay(), context));
        holder.temperature.setText(Utils.getTemperature(main.getMinTemperature(), main.getMaxTemperature()));
        holder.description.setText(main.getDescription());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("tempMin", mainList.get(position).getMinTemperature());
                intent.putExtra("tempMax", mainList.get(position).getMaxTemperature());
                intent.putExtra("desc", mainList.get(position).getDescription());
                intent.putExtra("date", mainList.get(position).getDay());
                intent.putExtra("img", mainList.get(position).getImg());
                intent.putExtra("tempMorn",mainList.get(position).getMornTemperature());
                intent.putExtra("tempNight",mainList.get(position).getNightTemperature());
                intent.putExtra("humidity",mainList.get(position).getHumidity());
                intent.putExtra("pressure",mainList.get(position).getPressure());
                intent.putExtra("rain",mainList.get(position).getRain());
                intent.putExtra("cloud",mainList.get(position).getCloud());
                intent.putExtra("wind",mainList.get(position).getWind());
                context.startActivity(intent);
//                overridePendingTransition(R.anim.anim_2, R.anim.anim_1);
            }
        });

        Picasso.with(context)
                .load("http://openweathermap.org/img/w/" + main.getImg() + ".png")
                .resize(150, 150)
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return mainList.size();
    }
}

