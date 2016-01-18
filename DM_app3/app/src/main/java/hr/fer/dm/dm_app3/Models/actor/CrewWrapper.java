package hr.fer.dm.dm_app3.Models.actor;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CrewWrapper implements Serializable {

    @SerializedName("data")
    private CrewList crewList;

    public CrewList getCrewList(){
        return crewList;
    }

}
