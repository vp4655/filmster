package hr.fer.dm.dm_app3.Models.api;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import hr.fer.dm.dm_app3.Models.genres.Genre;

/**
 * Created by Kajkara on 17.1.2016..
 */
public class MovieApi implements Serializable{

    @SerializedName("actors")
    String actors;

    public String getActors()
    {
        return actors;
    }


    @SerializedName("genres")
    List<Genre> genres;
    public List<Genre> getgenres(){
        return genres;
    }


    @SerializedName("imdb_id")
    String imdb_id;
    public String getimdb_id(){
        return imdb_id;
    }

    @SerializedName("imdb_poster_link")
    String imdb_poster_link;
    public String getimdb_poster_link(){
        return imdb_poster_link;
    }


    @SerializedName("imdb_rating")
    String imdb_rating;
    public String getimdb_rating(){
        return imdb_rating;
    }
    @SerializedName("metascore")
    String metascore;
    public String getmetascore(){
        return metascore;
    }

    @SerializedName("movieId")
    int movieId;
    public int getmovieId(){
        return movieId;
    }

    @SerializedName("popularity")
    float popularity;
    public float getpopularity(){
        return popularity;
    }

    @SerializedName("poster_path")
    String poster_path;
    public String getposter_path(){
        return poster_path;
    }

    @SerializedName("release_date")
    String release_date;
    public String getrelease_date(){
        return release_date;
    }

    @SerializedName("title")
    String title;

    public String getTitle()
    {
        return title;
    }

    @SerializedName("tomato_rating")
    String tomato_rating;

    public String gettomato_rating()
    {
        return tomato_rating;
    }
    @SerializedName("tomato_reviews")
    String tomato_reviews;

    public String gettomato_reviews()
    {
        return tomato_reviews;
    }

    @SerializedName("tomato_rotten")
    String tomato_rotten;

    public String gettomato_rotten()
    {
        return tomato_rotten;
    }


}
