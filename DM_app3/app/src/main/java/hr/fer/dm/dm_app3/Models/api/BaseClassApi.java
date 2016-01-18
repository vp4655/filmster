package hr.fer.dm.dm_app3.Models.api;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Kajkara on 17.1.2016..
 */
public class BaseClassApi implements Serializable {

    @SerializedName("code")
    String code;

    public String getCode()
    {
        return code;
    }

    @SerializedName("message")
    String message;

    public String getmessage()
    {
        return message;
    }

}
