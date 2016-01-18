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


    @SerializedName("cast")
    List<CastApi> cast;

    public List<CastApi> getCast()
    {
        return cast;
    }

    @SerializedName("director")
    String director;
    public String getdirector(){
        return director;
    }

    @SerializedName("genres")
    List<Genre> genres;
    public List<Genre> getgenres(){
        return genres;
    }

    @SerializedName("homepage")
    String homepage;
    public String gethomepage(){
        return homepage;
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

    @SerializedName("imdb_votes")
    String imdb_votes;
    public String getimdb_votes(){
        return imdb_votes;
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

    @SerializedName("overview")
    String overview;
    public String getoverview(){
        return overview;
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

    @SerializedName("runtime")
    String runtime;
    public String getruntime(){
        return runtime;
    }

    @SerializedName("title")
    String title;

    public String getTitle()
    {
        return title;
    }

    @SerializedName("tomato_fresh")
    String tomato_fresh;

    public String getTomato_fresh()
    {
        return tomato_fresh;
    }

    @SerializedName("tomato_meter")
    String tomato_meter;

    public String gettomato_meter()
    {
        return tomato_meter;
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

    @SerializedName("tomato_text_review")
    String tomato_text_review;

    public String gettomato_text_review()
    {
        return tomato_text_review;
    }

    @SerializedName("tomato_user_meter")
    String tomato_user_meter;
    public String gettomato_user_meter()
    {
        return tomato_user_meter;
    }

    @SerializedName("tomato_user_rating")
    String tomato_user_rating;
    public String gettomato_user_rating()
    {
        return tomato_user_rating;
    }

    @SerializedName("tomato_user_reviews")
    String tomato_user_reviews;
    public String gettomato_user_reviews()
    {
        return tomato_user_reviews;
    }

    @SerializedName("vote_average")
    float vote_average;
    public float getvote_average()
    {
        return vote_average;
    }

    @SerializedName("vote_count")
    int vote_count;
    public int getvote_count()
    {
        return vote_count;
    }

    @SerializedName("writers")
    String writers;
    public String getwriters()
    {
        return writers;
    }
}
