package hr.fer.dm.dm_app3.Models.actor;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import hr.fer.dm.dm_app3.Activites.LoginActivity;

public class ActorMinified implements Serializable{

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("profile_path")
    private String profilePictureUrl;

    @SerializedName("character")
    private String characterName;
    private static final long serialVersionUID = -8959832007991513854L;

    public ActorMinified(){

    }

    public static ActorMinified fromJson(JSONObject jsonObject){
        ActorMinified actor = new ActorMinified();
        try {
            actor.id = jsonObject.getInt("id");
            actor.name = jsonObject.getString("name");
            actor.profilePictureUrl = (jsonObject.has("profile_path") && !jsonObject.isNull("profile_path")) ? jsonObject.getString("profile_path") : "";
            actor.characterName = jsonObject.getString("character");
        }
        catch (JSONException e){
            e.printStackTrace();
            return null;
        }
        return actor;
    }

    public static ArrayList<ActorMinified> fromJson(JSONArray jsonArray){
        ArrayList<ActorMinified> actorsMin = new ArrayList<ActorMinified>(jsonArray.length());

        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject actorJson = null;

            try {
                actorJson = jsonArray.getJSONObject(i);
            }
            catch (Exception e){
                e.printStackTrace();
                continue;
            }

            ActorMinified actorMin = ActorMinified.fromJson(actorJson);
            if(actorMin != null){
                actorsMin.add(actorMin);
            }
        }

        return actorsMin;
    }


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

    public String getCharacterName(){
        return this.characterName;
    }
    public void setCharacterName(String characterName){
        this.characterName = characterName;
    }

    //endregion

}
