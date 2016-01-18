package hr.fer.dm.dm_app3.Models.actor;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import hr.fer.dm.dm_app3.ListViewItems.Actor;

/**
 * Created by Valentino on 18.1.2016..
 */
public class ActorWrapper implements Serializable {

    @SerializedName("data")
    private ActorDetail actorDetail;

    public ActorDetail getActorDetail(){
        return actorDetail;
    }

}
