package hr.fer.dm.dm_app3.Models.themoviedb;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;

import hr.fer.dm.dm_app3.Activites.LoginActivity;

public class MovieMinified implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("poster_path")
    private String posterPictureUrl;

    @SerializedName("character")
    private String characterName;
    private static final long serialVersionUID = -8959832007991513854L;

    public MovieMinified(){

    }


    //region Getters and Setters

    public int getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public String getPosterPictureUrl(){
        return LoginActivity.IMAGE_URL + this.posterPictureUrl;
    }

    public String getCharacterName(){
        return this.characterName;
    }

    //endregion

}
