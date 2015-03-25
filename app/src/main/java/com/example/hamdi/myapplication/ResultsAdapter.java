package com.example.hamdi.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by hamdi on 14/01/15.
 */
public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ResultsViewHolder> {
    private ArrayList<FilmDetails> list;
    Context context;

    public  ResultsAdapter(ArrayList<FilmDetails> list){
        this.list=list;    }
View view ;

    @Override
    public ResultsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.
                from(parent.getContext()).

                inflate(R.layout.card_view, parent, false);
        context = view.getContext();
        return new ResultsViewHolder(view);


    }

    @Override
    public void onBindViewHolder(final ResultsViewHolder holder, int position) {
//        new BindCard().execute(position,holder);

        //FilmDetails details = list.get(position);
        holder.title.setText(list.get(position).getTitle());
        holder.year.setText(list.get(position).getYear());
        holder.hiddenId.setText(position+"");

       if(list.get(position).getPoster().equals("N/A")) {
            Picasso.with(context).load(R.drawable.fail).into(holder.poster, new Callback() {
                @Override
                public void onSuccess() {
                    holder.progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onError() {

                }
            });
        }else {
           Picasso.with(context).load(list.get(position).getPoster()).into(holder.poster, new Callback() {
               @Override
               public void onSuccess() {
                   holder.progressBar.setVisibility(View.GONE);
               }

               @Override
               public void onError() {

               }
           });

       }
        }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BindCard extends AsyncTask<Object, Void,Void>{


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //view.findViewById(R.id.loadingPanel).setVisibility(View.GONE);
        }

        @Override
        protected Void doInBackground(Object... params) {
            FilmDetails details = list.get((int)params[0]);
            ((ResultsViewHolder) params[1]).title.setText(details.getTitle());
            ((ResultsViewHolder) params[1]).year.setText(details.getYear());
            ((ResultsViewHolder) params[1]).hiddenId.setText((int)params[0]+"");
            if(details.getPoster().equals("N/A")){

                Picasso.with(context).load("http://www.distritonline.pt/wp-content/plugins/nertworks-all-in-one-social-share-tools/images/no_image.png").into((((ResultsViewHolder) params[1])).poster);}
            else{
                Picasso.with(context).load(details.getPoster()).into((((ResultsViewHolder) params[1]).poster));
            }


            return null;
        }
    }

    public class ResultsViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView year;
        TextView hiddenId;
        ImageView poster;
        ProgressBar progressBar;
        public ResultsViewHolder(View itemView) {

            super(itemView);
            hiddenId = (TextView) itemView.findViewById(R.id.hiddenId);
            title = (TextView) itemView.findViewById(R.id.title);
            year = (TextView) itemView.findViewById(R.id.year);
            poster= (ImageView) itemView.findViewById(R.id.poster);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {
                    //Log.d("Json to send",""+new Gson().toJson(list.get(Integer.parseInt(hiddenId.getText().toString()))));
                    Log.d("ID",hiddenId.getText().toString());
                    context.startActivity(new Intent(context,Details.class).putExtra("data",list.get(Integer.parseInt(hiddenId.getText().toString())).getImdbID()));
                    //Toast.makeText(context,"Click"+hiddenId.getText().toString(),Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

}
