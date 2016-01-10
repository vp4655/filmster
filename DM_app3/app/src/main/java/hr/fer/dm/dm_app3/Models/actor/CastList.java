package hr.fer.dm.dm_app3.Models.actor;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CastList {

    @SerializedName("id")
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
        return cast.subList(0, 6);
    }

}
