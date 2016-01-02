package hr.fer.dm.dm_app3.Models.themoviedb;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MovieDetail implements Serializable {

    @SerializedName("poster_path")
    private String image;

    @SerializedName("adult")
    private Boolean adult;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String release_date;

    @SerializedName("homepage")
    private String homepage;

    @SerializedName("id")
    private int id;

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

    public int getId()
    {
        return id;
    }

    public String getHomepage() { return homepage; }

    public String getOriginal_title()
    {
        return original_title;
    }

    public String getBackdrop_path()
    {
        return backdrop_path;
    }

    public Float getPopularity()
    {
        return popularity;
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
