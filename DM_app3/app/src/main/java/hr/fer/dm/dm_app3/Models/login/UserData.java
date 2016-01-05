package hr.fer.dm.dm_app3.Models.login;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kajkara on 5.1.2016..
 */
public class UserData {

    @SerializedName("token")
    private String token;

    @SerializedName("user")
    private User user;

    public String getToken()
    {
        return token;
    }

    public User getUser()
    {
        return user;
    }
}



