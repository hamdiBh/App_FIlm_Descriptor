package com.example.hamdi.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by hamdi on 13/01/15.
 */
public class JsonParser {

    public String getResult(String url) {


        try {
            URL u = new URL(url);
            HttpURLConnection c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);

            c.connect();
            int status = c.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    return sb.toString();
            }

        } catch (MalformedURLException ex) {
            Log.d("error", ex.getMessage());
        } catch (IOException ex) {
            Log.d("erro", ex.getMessage());
        }
        return null;
    }

    /*public FilmDetails getDetails(String imdbID) {
        String url = "http://www.omdbapi.com/?i=" + imdbID+"&plot=full";

        String data = getResult(url);
        try {
            JSONObject jsonObject = new JSONObject(data);
             //Log.d("url loop", data);
            return new Gson().fromJson(data, FilmDetails.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }*/



    public FilmDetails getDetails(String imdbID) {
        String url = "http://www.omdbapi.com/?i=" + imdbID+"&plot=full";

        String data = null;
        try {
            data = new getJson().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,url).get();
            JSONObject jsonObject = new JSONObject(data);
             //Log.d("url loop", data);
            return new Gson().fromJson(data, FilmDetails.class);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<FilmDetails> getItems(String title, String year,String type) throws ExecutionException, InterruptedException {
        String url = "http://msese.6te.net/filmdescriptor/?type=" + type + "&s=" + title + "&y=" + year;
        //Log.d("hello wrold",url);
        String data="";
        try {
            data = new getJson().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,url).get();
            //data = getResult(url);
        }catch (Exception e){
            Log.d("loading fails",e.getMessage());
        }

        //Log.d("hello wrold","lol4");
        ArrayList<FilmDetails> searchItems = new ArrayList<FilmDetails>();
        try {

            JSONObject jsonObject = new JSONObject(data);
            JSONArray result = jsonObject.getJSONArray("Search");
             //Log.d("url loop", data);
            SearchItem[] items = new Gson().fromJson(result.toString(), SearchItem[].class);
            for(int i=0 ; i<items.length ; i++){
                searchItems.add(new FilmDetails(items[i].getTitle(),items[i].getYear(),items[i].getImdbID(),items[i].getPoster()));
            }
            return searchItems;
        } catch (JSONException e) {
            Log.d("erreur",e.getMessage());
            e.printStackTrace();
        }

        return null;
    }



    public ArrayList<FilmDetails> getDetails(String title, String year, String type) {
        ArrayList<FilmDetails> list = new ArrayList<FilmDetails>();
        String url = "http://www.omdbapi.com/?type=" + type + "&s=" + title + "&y=" + year + "&plot=full&r=json";
        String data = null;
        try {
            data = new getJson().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        // Log.d("title", title);
       // Log.d("json", data);
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray result = jsonObject.getJSONArray("Search");
           // Log.d("Result", result.toString());
            Gson s = new Gson();
            SearchItem[] details = s.fromJson(result.toString(), SearchItem[].class);
            for (int i = 0; i < details.length; i++) {
                list.add(getDetails(details[i].getImdbID()));
               // Log.d("i", i + "");
            }


        } catch (JSONException e) {
            Log.d("error", e.getMessage());
            e.printStackTrace();
        }

        return list;
    }


    private  class getJson extends AsyncTask<String,Void,String>{



        @Override
        protected String doInBackground(String... params) {
            try {
                //Log.d("hello wrold","lol2");
                URL u = new URL(params[0]);
                HttpURLConnection c = (HttpURLConnection) u.openConnection();
                c.setRequestMethod("GET");
                c.setRequestProperty("Content-length", "0");
                c.setUseCaches(false);
                c.setAllowUserInteraction(false);

                c.connect();
                int status = c.getResponseCode();

                switch (status) {
                    case 200:
                    case 201:
                        BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        String line;
                        while ((line = br.readLine()) != null) {
                            sb.append(line + "\n");
                        }
                        br.close();
                        return sb.toString();
                }

            } catch (MalformedURLException ex) {
                Log.d("error", ex.getMessage());
            } catch (IOException ex) {
                Log.d("erro", ex.getMessage());
            }
            return null;
        }

        }
    }
