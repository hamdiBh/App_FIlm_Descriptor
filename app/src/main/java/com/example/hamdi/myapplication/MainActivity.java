package com.example.hamdi.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends ActionBarActivity {
    Button search;
    EditText title;
    EditText year;
    Spinner type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy tp = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(tp);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search = (Button) findViewById(R.id.search);
        title = (EditText) findViewById(R.id.title);
        year = (EditText) findViewById(R.id.year);
        type = (Spinner) findViewById(R.id.type);

        search.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                boolean exit = false;
                title.setError(null);
                if (title.getText().toString().equals("")) {
                    title.setError("Please verify your input");
                    exit = true;
                }
                int yearIn = 0;


                if (year.getText().toString().equals("")) {
                    yearIn = 1950;
                } else {
                    try {
                        yearIn = Integer.parseInt(year.getText().toString());
                    } catch (Exception e) {
                        Log.d("year error ", e.getMessage());
                    }
                }
                if (yearIn == 0 || yearIn > Calendar.getInstance().get(Calendar.YEAR) || yearIn < 1950) {
                    year.setError("Please enter a correct year");
                    exit = true;

                }

                if (exit == false) {


                    // Toast.makeText(getApplicationContext(),"loool",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Info.class).putExtra("title", title.getText().toString().replaceAll("\\s", "%20")).putExtra("type", type.getSelectedItem().toString().toLowerCase()).putExtra("year", year.getText().toString()));
                }

            }
        });

        title.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!title.getText().toString().equals("")) {
                    title.setError(null);
                }
            }
        });


    }

    protected void onDestroy() {
        super.onDestroy();
        try {
            trimCache(this);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void trimCache(Context context) {
        try {
            File dir = context.getCacheDir();
            if (dir != null && dir.isDirectory()) {
                deleteDir(dir);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        // The directory is now empty so delete it
        return dir.delete();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
