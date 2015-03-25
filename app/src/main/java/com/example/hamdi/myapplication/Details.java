package com.example.hamdi.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.concurrent.ExecutionException;


public class Details extends ActionBarActivity {

    TextView title;
    ImageView pos;
    TextView desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        //Log.d("recived",getIntent().getStringExtra("data").toString());
        //FilmDetails details = new JsonParser().getDetails(new Gson().fromJson( getIntent().getStringExtra("data").toString(),FilmDetails.class).getImdbID());
        //FilmDetails details;
        title = (TextView) findViewById(R.id.aa);
        pos = (ImageView) findViewById(R.id.p);
        desc = (TextView) findViewById(R.id.desc);


            //Log.d("Details",details.toString());

            new LoadDetails().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,new String[]  { getIntent().getStringExtra("data").toString()});


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class LoadDetails extends AsyncTask<String ,Void , FilmDetails>{

        @Override
        protected void onPostExecute(FilmDetails filmDetails) {
            super.onPostExecute(filmDetails);
            findViewById(R.id.loadingPanel).setVisibility(View.GONE);
            Log.d("film",filmDetails.toString());
            title.setText(filmDetails.getTitle());
            desc.setText(filmDetails.getPlot());
            Picasso.with(getApplicationContext()).load(filmDetails.getPoster()).into(pos);


        }

        @Override
        protected FilmDetails doInBackground(String... params) {

            return new  JsonParser().getDetails(params[0]);


        }
    }
}
