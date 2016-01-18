package hr.fer.dm.dm_app3.Models.api;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kajkara on 17.1.2016..
 */
public class CastApi {

    @SerializedName("cast_id")
    int cast_id;
    public int getcast_id()
    {
        return cast_id;
    }

    @SerializedName("character")
    String character;
    public String getcharacter()
    {
        return character;
    }

    @Nullable
    @SerializedName("id")
    int id;
    public int getid()
    {
        return id;
    }

    @SerializedName("name")
    String name;
    public String getname()
    {
        return name;
    }


//    {
//            "credit_id" : "52fe4212c3a36847f8001b31",

//            "order" : 0,

    @SerializedName("profile_path")
    String profile_path;
    public String getprofile_path()
    {
        return profile_path;
    }
}
