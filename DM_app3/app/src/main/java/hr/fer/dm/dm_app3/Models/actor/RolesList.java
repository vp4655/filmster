package hr.fer.dm.dm_app3.Models.actor;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import hr.fer.dm.dm_app3.ListViewItems.Movie;
import hr.fer.dm.dm_app3.Models.themoviedb.MovieMinified;

public class RolesList implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("cast")
    private List<MovieMinified> cast;

    @SerializedName("crew")
    private List<MovieMinified> crew;

    public int getId() {
        return id;
    }

    public List<MovieMinified> getCast() {

        Map<String, MovieMinified> filterd = new HashMap<String, MovieMinified>();
        List<MovieMinified> temp = new ArrayList<MovieMinified>(cast);
        temp.addAll(crew);

        for(MovieMinified movie : temp){
            if(!filterd.containsKey(movie.getTitle())){
                filterd.put(movie.getTitle(), movie);
            }
        }

        return new ArrayList<MovieMinified>(filterd.values());
    }

    public List<MovieMinified> getSmallRoles(){

        Map<String, MovieMinified> filterd = new HashMap<String, MovieMinified>();
        List<MovieMinified> temp = new ArrayList<MovieMinified>(cast);
        temp.addAll(crew);

        for(MovieMinified movie : temp){
            if(!filterd.containsKey(movie.getTitle())){
                filterd.put(movie.getTitle(), movie);
            }
        }

        if(filterd.size() > 0){
            return new ArrayList<MovieMinified>(filterd.values()).subList(0, 6 < filterd.size() ? 6 : filterd.size() - 1);
        }
        else {
            return new ArrayList<MovieMinified>(filterd.values());
        }
    }

}
