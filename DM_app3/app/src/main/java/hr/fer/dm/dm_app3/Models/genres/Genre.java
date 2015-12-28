package hr.fer.dm.dm_app3.Models.genres;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kajkara on 28.12.2015..
 */
public class Genre {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    public int getId() {
        return id;
    }

    public String getName()
    {
        return name;
    }

}
