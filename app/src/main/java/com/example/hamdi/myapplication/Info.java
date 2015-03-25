package com.example.hamdi.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class Info extends ActionBarActivity {
Context context;
    RecyclerView recList;
    LinearLayoutManager llm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        try {


            recList = (RecyclerView) findViewById(R.id.cardList);
            recList.setHasFixedSize(true);
            llm= new LinearLayoutManager(getApplicationContext());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recList.setLayoutManager(llm);

            String title = getIntent().getStringExtra("title");
            String year = getIntent().getStringExtra("year");
            String type = getIntent().getStringExtra("type");

            new loadPrez().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,title,year,type);
            //new loadPrez().execute(title,year,type);

        }catch (Exception e){
            Log.d("Error",e.getMessage());
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_info, menu);
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

    public class loadPrez extends AsyncTask<String, Void, ArrayList<FilmDetails>> {





        /*public <T> void execute(AsyncTask<T,?,?> task, T... args) {
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, args);
        }*/
        @Override
        protected void onPostExecute(ArrayList<FilmDetails> filmDetailses) {
            ArrayList<FilmDetails> details = filmDetailses;
            findViewById(R.id.loadingPanel).setVisibility(View.GONE);
            ResultsAdapter adapter = new ResultsAdapter(details);
            //Log.d("Adapter Size",adapter.getItemCount()+"");
            recList.setAdapter(adapter);

            adapter.notifyDataSetChanged();

        }

        @Override
        protected ArrayList<FilmDetails> doInBackground(String... params) {

           try {
                //Log.d("hello wrold","lol");
                return new JsonParser().getItems(params[0],params[1],params[2]);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        return null;
           // return new JsonParser().getDetails(params[0],params[1],params[2]);
        }
    }

}
