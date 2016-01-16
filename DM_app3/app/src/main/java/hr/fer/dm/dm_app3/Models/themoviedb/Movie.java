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

    @SerializedName("adult")
    private Boolean adult;

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

    @SerializedName("original_title")
    private String original_title;

    @SerializedName("title")
    private String title;

    @SerializedName("backdrop_path")
    private String backdrop_path;

    @SerializedName("popularity")
    private Float popularity;

    @SerializedName("vote_count")
    private int vote_count;

    @SerializedName("video")
    private Boolean video;

    @SerializedName("vote_average")
    private Float vote_average;


    public String getTitle() {
        return title;
    }

    public Boolean getAdult()
    {
        return adult;
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

    public String getOriginal_title()
    {
        return original_title;
    }

    public String getBackdrop_path()
    {
        return backdrop_path;
    }

    public String getPopularity()
    {
//        DecimalFormat df = new DecimalFormat("#.##");
//        df.setRoundingMode(RoundingMode.CEILING);
//        return df.format(popularity);
        return Float.toString(25.0f);
    }

    public String getImdb_id(){
        return imdb_id;
    }

    public int getVote_count()
    {
        return vote_count;
    }

    public Boolean getVideo()
    {
        return video;
    }

    public Float getVote_average()
    {
        return vote_average;
    }

    public String getImage() {
        return "https://image.tmdb.org/t/p/w396/" + image;

    }
}
