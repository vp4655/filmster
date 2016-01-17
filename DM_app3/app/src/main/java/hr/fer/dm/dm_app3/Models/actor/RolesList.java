package hr.fer.dm.dm_app3.Models.actor;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import hr.fer.dm.dm_app3.Models.themoviedb.MovieMinified;

public class RolesList implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("cast")
    private List<MovieMinified> cast;

    public int getId() {
        return id;
    }

    public List<MovieMinified> getCast() {
        return cast;
    }

    public List<MovieMinified> getSmallRoles(){
        return cast.subList(0, 6);
    }

}
