package hr.fer.dm.dm_app3.Models.actor;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hr.fer.dm.dm_app3.Models.themoviedb.MovieMinified;

public class CastList {

    @SerializedName("movieId")
    private int id;

    @SerializedName("cast")
    private List<ActorMinified> cast;

    public int getId() {
        return id;
    }

    public List<ActorMinified> getCast() {

        return cast;
    }

    public List<ActorMinified> getSmallCast(){

        if(cast.size() > 0){
            return cast.subList(0, 6 < cast.size() ? 6 : cast.size() - 1);
        }
        else {
            return cast;
        }
    }

}
