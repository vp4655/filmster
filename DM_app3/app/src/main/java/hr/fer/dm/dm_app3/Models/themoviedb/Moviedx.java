package hr.fer.dm.dm_app3.Models.themoviedb;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Kajkara on 18.12.2015..
 */
public class Moviedx {

    @SerializedName("page")
    private int page;

    @SerializedName("results")
    private List<Movie> movieList;

    public List<Movie> getMovieList() {
        return movieList;
    }
}

