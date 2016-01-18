package hr.fer.dm.dm_app3.Models;

import android.content.Context;
import android.content.Intent;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import hr.fer.dm.dm_app3.Activites.HomeActivity;
import hr.fer.dm.dm_app3.Activites.LoginActivity;


/**
 * Created by Kajkara on 17.1.2016..
 */
public class ErrorModel implements Serializable {
//    {
//        "code": "E_USER_NOT_FOUND",
//            "message": "User with specified credentials is not found",
//            "data": {}
//    }
//
    @SerializedName("code")
    private String code;

    @SerializedName("message")
    private String message;


    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Intent actionForStatusCode(int status, Context context)
    {
        Intent intent = new Intent(context, HomeActivity.class);
        if(status==401)
        {
            intent = new Intent(context, LoginActivity.class);
        }

        return intent;
    }

}
