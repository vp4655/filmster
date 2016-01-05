package hr.fer.dm.dm_app3.Models.login;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kajkara on 4.1.2016..
 */
public class LoginResponse {

//    code: "OK",
//    message: "Operation is successfully executed",
//    data: {

    @SerializedName("code")
    private String code;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private UserData userData;


    public String getCode() {
        return code;
    }

    public String getMessage()
    {
        return message;
    }

    public UserData getUserData()
    {
        return userData;
    }

}
