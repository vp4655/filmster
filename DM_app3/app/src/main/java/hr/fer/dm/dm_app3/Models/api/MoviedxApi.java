package hr.fer.dm.dm_app3.Models.api;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import hr.fer.dm.dm_app3.ListViewItems.Movie;

/**
 * Created by Kajkara on 17.1.2016..
 */
public class MoviedxApi extends BaseClassApi {

    @SerializedName("data")
    List<MovieApi> movieList;

    public List<MovieApi> getMovieList()
    {
        return movieList;
    }

//    "criteria": {},


    @SerializedName("limit")
    int limit;
    public int getlimit()
    {
        return limit;
    }

    @SerializedName("start")
    int start;
    public int getstart()
    {
        return start;
    }

    @SerializedName("end")
    int end;
    public int getend()
    {
        return end;
    }

    @SerializedName("page")
    int page;
    public int getpage()
    {
        return page;
    }

}
