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

        Map<String, ActorMinified> filterd = new HashMap<String, ActorMinified>();
        List<ActorMinified> temp = new ArrayList<ActorMinified>(cast);

        for(ActorMinified movie : temp){
            if(!filterd.containsKey(movie.getName())){
                filterd.put(movie.getName(), movie);
            }
        }

        return new ArrayList<>(filterd.values());
    }

    public List<ActorMinified> getSmallCast(){

        Map<String, ActorMinified> filterd = new HashMap<String, ActorMinified>();
        List<ActorMinified> temp = new ArrayList<ActorMinified>(cast);

        for(ActorMinified movie : temp){
            if(!filterd.containsKey(movie.getName())){
                filterd.put(movie.getName(), movie);
            }
        }

        if(filterd.size() > 0){
            return new ArrayList<ActorMinified>(filterd.values()).subList(0, 6 < filterd.size() ? 6 : filterd.size() - 1);
        }
        else {
            return new ArrayList<ActorMinified>(filterd.values());
        }
    }

}
