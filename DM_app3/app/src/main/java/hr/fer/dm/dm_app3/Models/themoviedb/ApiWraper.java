package hr.fer.dm.dm_app3.Models.themoviedb;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Valentino on 18.1.2016..
 */
public class ApiWraper implements Serializable {

    @SerializedName("data")
    MovieDetail movie;

    public MovieDetail getMovie() {
        return movie;
    }
}
