package com.example.hamdi.myapplication;

/**
 * Created by hamdi on 14/01/15.
 */
public class SearchItem {

    String Title, Year, imdbID, Poster;

    @Override
    public String toString() {
        return "SearchItem{" +
                "Title='" + Title + '\'' +
                ", Year='" + Year + '\'' +
                ", imdbID='" + imdbID + '\'' +
                ", Poster='" + Poster + '\'' +
                '}';
    }

    public SearchItem(String title, String year, String imdbID, String poster) {
        Title = title;
        Year = year;
        this.imdbID = imdbID;
        Poster = poster;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setYear(String year) {
        Year = year;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public String getTitle() {
        return Title;
    }

    public String getYear() {
        return Year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getPoster() {
        return Poster;
    }
}
