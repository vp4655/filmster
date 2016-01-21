package hr.fer.dm.dm_app3.Models.themoviedb;

import com.google.gson.annotations.SerializedName;

import android.text.TextUtils;

import java.io.Serializable;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Kajkara on 18.12.2015..
 */
public class Movie implements Serializable {

    @SerializedName("poster_path")
    private String image;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String release_date;

    @SerializedName("genre_ids")
    private List<Integer> genre_ids;
    private List<String> genres;

    @SerializedName("id")
    private int id;

    @SerializedName("imdb_id")
    private String imdb_id;

    @SerializedName("imdb_rating")
    private String imdb_rating;

    @SerializedName("title")
    private String title;

    @SerializedName("metascore")
    private String metascore;

    @SerializedName("runtime")
    private int runtime;

    @SerializedName("tomato_meter")
    private String tomato_meter;

    @SerializedName("tomato_user_meter")
    private String tomato_user_meter;

    @SerializedName("popularity")
    private Float popularity;

    @SerializedName("vote_average")
    private Float vote_average;


    public String getTitle() {
        return title;
    }

    public String getOverview()
    {
        return overview;
    }

    public String getRelease_date()
    {
        return release_date;
    }

    public List<Integer> getGenre_ids()
    {
        return genre_ids;
    }
    public List<String> getGenres()
    {
        return genres;
    }
    public void setGenres(List<String> genres)
    {
        this.genres = genres;
    }

    public int getId()
    {
        return id;
    }

    public String getRuntime(){
        return Integer.toString(runtime);
    }

    public String getImdb_rating(){
        return imdb_rating;
    }

    public String getMetascore(){
        return metascore;
    }

    public String getTomato_meter(){
        return tomato_meter;
    }

    public String getTomato_user_meter(){
        return tomato_user_meter;
    }

    public String getPopularity()
    {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        return df.format(popularity).toString();
        //return Float.toString(25.0f);
    }

    public String getImdb_id(){
        return imdb_id;
    }

    public Float getVote_average()
    {
        return vote_average;
    }

    public String getImage() {
        return "https://image.tmdb.org/t/p/w396/" + image;

    }
}
