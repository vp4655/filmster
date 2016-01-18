package hr.fer.dm.dm_app3.Models.actor;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Valentino on 18.1.2016..
 */
public class CastWrapper implements Serializable {

    @SerializedName("data")
    private CastList castList;

    public CastList getCastList(){
        return castList;
    }

}
