package hr.fer.dm.dm_app3.Models.login;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kajkara on 5.1.2016..
 */
public class User {
    @SerializedName("username")
    private String username;

    @SerializedName("email")
    private String email;

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("photo")
    private String photo;

    @SerializedName("createdAt")
    private String createdAt;

    @SerializedName("updatedAt")
    private String updatedAt;

    @SerializedName("id")
    private String id;

    public String getUsername() {
        return username;
    }

    public String getEmail()
    {
        return email;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getPhoto()
    {
        return photo;
    }

    public String getId()
    {
        return id;
    }

}

