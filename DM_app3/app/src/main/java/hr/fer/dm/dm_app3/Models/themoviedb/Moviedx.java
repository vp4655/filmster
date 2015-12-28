package hr.fer.dm.dm_app3.Models.themoviedb;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Kajkara on 18.12.2015..
 */
public class Moviedx {

    @SerializedName("page")
    private int page;

    @SerializedName("results")
    private List<Movie> movieList;

    @SerializedName("total_pages")
    private int totalPages;

    public List<Movie> getMovieList(HashMap<Integer, String> genresDict) {
        setGenres(genresDict);
        return movieList;
    }

    public int getTotalPages() {
        return totalPages;
    }

    private void setGenres(HashMap<Integer, String> genresDict)
    {
        for (Movie movie : movieList) {
            List<Integer> genres = movie.getGenre_ids();
            List<String> genresString = new ArrayList<String>();
            for (Integer genreId:genres) {
                genresString.add(genresDict.get(genreId));
            }
            movie.setGenres(genresString);
        }
    }
}

