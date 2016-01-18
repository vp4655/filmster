package hr.fer.dm.dm_app3.Models.actor;

import com.google.gson.annotations.SerializedName;

public class ActorDetail {

    @SerializedName("profile_picture")
    private String image;

    @SerializedName("adult")
    private Boolean adult;

    @SerializedName("biography")
    private String biography;

    @SerializedName("birthday")
    private String birthday;

    @SerializedName("deathday")
    private String deathday;

    @SerializedName("homepage")
    private String homepage;

    @SerializedName("actorId")
    private int id;

    @SerializedName("imdb_id")
    private String imdb_id;

    @SerializedName("name")
    private String name;

    @SerializedName("place_of_birth")
    private String place_of_birth;

    @SerializedName("popularity")
    private Float popularity;

    public String getBiography() {
        return biography;
    }

    public Boolean getAdult()
    {
        return adult;
    }

    public int getId()
    {
        return id;
    }

    public String getHomepage() { return homepage; }

    public Float getPopularity()
    {
        return popularity;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getDeathday() {
        return deathday;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public String getName() {
        return name;
    }

    public String getPlace_of_birth() {
        return place_of_birth;
    }

    public String getImage() {
        return "https://image.tmdb.org/t/p/w396/" + image;

    }

}
