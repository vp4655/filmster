package hr.fer.dm.dm_app3.Models.themoviedb;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import hr.fer.dm.dm_app3.Network.ApiManager;

/**
 * Created by Kajkara on 18.12.2015..
 */
public class Sprite implements Serializable {

    @SerializedName("poster_path")
    private String image;

    public String getImage() {
        return "https://image.tmdb.org/t/p/w396/"+image;
    }
}

