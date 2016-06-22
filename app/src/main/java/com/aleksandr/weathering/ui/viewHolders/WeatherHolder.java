package com.aleksandr.weathering.ui.viewHolders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aleksandr.weathering.R;

/**
 * Created by a.afanasiev on 15.06.2016.
 */
public class WeatherHolder extends RecyclerView.ViewHolder {

    public TextView temperature;
    public TextView description;
    public TextView date;
    public CardView cardView;
    public ImageView img;

    public WeatherHolder(View itemView) {
        super(itemView);
        cardView = (CardView) itemView.findViewById(R.id.cardView);
        temperature = (TextView) itemView.findViewById(R.id.temperature);
        description = (TextView) itemView.findViewById(R.id.description);
        date = (TextView) itemView.findViewById(R.id.date);
        img = (ImageView) itemView.findViewById(R.id.img);
    }
}


