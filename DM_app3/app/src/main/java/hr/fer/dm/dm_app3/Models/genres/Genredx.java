package hr.fer.dm.dm_app3.Models.genres;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import hr.fer.dm.dm_app3.Models.themoviedb.Movie;

/**
 * Created by Kajkara on 28.12.2015..
 */
public class Genredx {
    @SerializedName("genres")
    private List<Genre> genreList;

    public List<Genre> getGenreList() {
        return genreList;
    }

}
