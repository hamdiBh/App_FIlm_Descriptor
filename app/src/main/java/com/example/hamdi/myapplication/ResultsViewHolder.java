package com.example.hamdi.myapplication;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by hamdi on 14/01/15.
 */
public class ResultsViewHolder extends RecyclerView.ViewHolder {

    TextView title;
    TextView year;
    TextView hiddenId;
    ImageView poster;
    public ResultsViewHolder(View itemView) {
        super(itemView);
        hiddenId = (TextView) itemView.findViewById(R.id.hiddenId);
        title = (TextView) itemView.findViewById(R.id.title);
        year = (TextView) itemView.findViewById(R.id.year);
        poster= (ImageView) itemView.findViewById(R.id.poster);
    }
}
