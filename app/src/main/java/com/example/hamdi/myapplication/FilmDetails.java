package com.example.hamdi.myapplication;

/**
 * Created by hamdi on 13/01/15.
 */
public class FilmDetails {
    String Title, Year, Rated, Released, Runtime, Genre, Director, Writer, Actors, Plot, Language, Country, Poster, Metascore, imdbRating, imdbVotes, imdbID, Type, Response;

    public FilmDetails(String title, String year, String imdbID, String poster) {
        Title = title;
        Year = year;
        this.imdbID = imdbID;
        Poster = poster;
    }

    public FilmDetails(String title, String year, String rated, String released, String runtime, String genre, String director, String writer, String actors, String plot, String language, String country, String poster, String metascore, String imdbRating, String imdbVotes, String imdbID, String type, String response) {
        Title = title;
        Year = year;
        Rated = rated;
        Released = released;
        Runtime = runtime;
        Genre = genre;
        Director = director;
        Writer = writer;
        Actors = actors;
        Plot = plot;
        Language = language;
        Country = country;
        Poster = poster;
        Metascore = metascore;
        this.imdbRating = imdbRating;
        this.imdbVotes = imdbVotes;
        this.imdbID = imdbID;
        Type = type;
        Response = response;
    }

    public String getTitle() {
        return Title;
    }

    public String getYear() {
        return Year;
    }

    public String getRated() {
        return Rated;
    }

    public String getReleased() {
        return Released;
    }

    public String getRuntime() {
        return Runtime;
    }

    public String getGenre() {
        return Genre;
    }

    public String getDirector() {
        return Director;
    }

    public String getWriter() {
        return Writer;
    }

    public String getActors() {
        return Actors;
    }

    public String getPlot() {
        return Plot;
    }

    public String getLanguage() {
        return Language;
    }

    public String getCountry() {
        return Country;
    }

    public String getPoster() {
        return Poster;
    }

    public String getMetascore() {
        return Metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getType() {
        return Type;
    }

    public String getResponse() {
        return Response;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setYear(String year) {
        Year = year;
    }

    public void setRated(String rated) {
        Rated = rated;
    }

    public void setReleased(String released) {
        Released = released;
    }

    public void setRuntime(String runtime) {
        Runtime = runtime;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public void setWriter(String writer) {
        Writer = writer;
    }

    public void setActors(String actors) {
        Actors = actors;
    }

    public void setPlot(String plot) {
        Plot = plot;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public void setMetascore(String metascore) {
        Metascore = metascore;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setResponse(String response) {
        Response = response;
    }
}
