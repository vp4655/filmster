package hr.fer.dm.dm_app3.ListViewItems;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import hr.fer.dm.dm_app3.LoginActivity;

/**
 * Created by Valentino on 11.12.2015..
 */
public class Actor implements Serializable{
    private int id;
    private String name;
    private String profilePictureUrl;
    private double popularity;
    private String birthDate;
    private String deathDate;
    private String imdbId;
    private String homepageUrl;
    private String placeOfBirth;
    private String biography;
    private static final long serialVersionUID = -8959832007991513854L;

    public Actor(){

    }

    /*public Actor(String name, String profilePictureUrl, double populatiry, Date birthDate, Date deathDate, String imdbId, String homepageUrl, String placeOfBirth, String biography){
        this.name = name;
        this.profilePictureUrl = profilePictureUrl;
        this.populatiry = populatiry;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.imdbId = imdbId;
        this.homepageUrl = homepageUrl;
        this.placeOfBirth = placeOfBirth;
        this.biography = biography;
    } mozda ne treba */

    public static Actor fromJson(JSONObject jsonObject){
        Actor actor = new Actor();
        try {
            actor.id = jsonObject.getInt("id");
            actor.name = jsonObject.getString("name");
            actor.profilePictureUrl = (jsonObject.has("profile_path") && !jsonObject.isNull("profile_path")) ? jsonObject.getString("profile_path") : "";
            actor.popularity = jsonObject.getDouble("popularity");
            actor.birthDate = (jsonObject.has("birthday") && !jsonObject.isNull("birthday")) ? jsonObject.getString("birthday") : "";
            actor.deathDate = (jsonObject.has("deathday") && !jsonObject.isNull("deathday")) ? jsonObject.getString("deathday") : "";
            actor.imdbId = (jsonObject.has("imdb_id") && !jsonObject.isNull("imdb_id")) ? jsonObject.getString("imdb_id") : "";
            actor.homepageUrl = (jsonObject.has("homepage") && !jsonObject.isNull("homepage")) ? jsonObject.getString("homepage") : "";
            actor.placeOfBirth = (jsonObject.has("place_of_birth") && !jsonObject.isNull("place_of_birth")) ? jsonObject.getString("place_of_birth") : "";
            actor.biography = (jsonObject.has("biography") && !jsonObject.isNull("biography")) ? jsonObject.getString("biography") : "";
        }
        catch (JSONException e){
            e.printStackTrace();
            return null;
        }
        return actor;
    }

    public static ArrayList<Actor> fromJson(JSONArray jsonArray){
        ArrayList<Actor> actors = new ArrayList<Actor>(jsonArray.length());

        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject actorJson = null;

            try {
                actorJson = jsonArray.getJSONObject(i);
            }
            catch (Exception e){
                e.printStackTrace();
                continue;
            }

            Actor actor = Actor.fromJson(actorJson);
            if(actor != null){
                actors.add(actor);
            }
        }

        return actors;
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
        return this.profilePictureUrl;
    }
    public void setProfilePictureUrl(String profilePictureUrl){
        this.profilePictureUrl = LoginActivity.IMAGE_URL + profilePictureUrl;
    }

    public  double getPopularity(){
        return this.popularity;
    }
    public void setPopularity(double popularity){
        this.popularity = popularity;
    }

    public String getBirthDate(){
        return this.birthDate;
    }
    public void setBirthDate(String birthDate){
        this.birthDate = birthDate;
    }

    public String getDeathDate(){
        return this.deathDate;
    }
    public void setDeathDate(String deathDate){
        this.deathDate = deathDate;
    }

    public String getImdbId(){
        return this.imdbId;
    }
    public void setImdbId(String imdbId){
        this.imdbId = imdbId;
    }

    public String getHomepageUrl(){
        return this.homepageUrl;
    }
    public void setHomepageUrl(String homepageUrl){
        this.homepageUrl = homepageUrl;
    }

    public String getPlaceOfBirth(){
        return this.placeOfBirth;
    }
    public void setPlaceOfBirth(String placeOfBirth){
        this.placeOfBirth = placeOfBirth;
    }

    public String getBiography(){
        return this.biography;
    }
    public void setBiography(String biography){
        this.biography = biography;
    }

    //endregion

}
