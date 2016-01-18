package hr.fer.dm.dm_app3.Models.actor;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import hr.fer.dm.dm_app3.Activites.LoginActivity;

/**
 * Created by Valentino on 18.1.2016..
 */
public class CrewMinified {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("profile_path")
    private String profilePictureUrl;

    @SerializedName("job")
    private String job;

    @SerializedName("department")
    private String department;
    private static final long serialVersionUID = -8959832007991513854L;



    //region Getters and Setters

    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getProfilePictureUrl(){
        return LoginActivity.IMAGE_URL + this.profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl){
        this.profilePictureUrl = LoginActivity.IMAGE_URL + profilePictureUrl;
    }

    public String getJob(){
        return this.job + "(" + this.department + ")";
    }

    //endregion

}
