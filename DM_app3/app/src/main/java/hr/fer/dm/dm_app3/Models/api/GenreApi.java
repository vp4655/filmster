package hr.fer.dm.dm_app3.Models.api;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Kajkara on 17.1.2016..
 */
public class GenreApi implements Serializable {

    @SerializedName("name")
    String name;
    public String getname(){
        return name;
    }

    @SerializedName("id")
    int id;
    public int getid(){
        return id;
    }



}
