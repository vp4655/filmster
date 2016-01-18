package hr.fer.dm.dm_app3.Models.actor;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CrewList {

    @SerializedName("movieId")
    private int id;

    @SerializedName("crew")
    private List<CrewMinified> crew;

    public int getId() {
        return id;
    }

    public List<CrewMinified> getCrew() {
        return crew;
    }

}
