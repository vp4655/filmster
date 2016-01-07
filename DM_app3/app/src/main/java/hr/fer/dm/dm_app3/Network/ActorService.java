package hr.fer.dm.dm_app3.Network;

import hr.fer.dm.dm_app3.Models.actor.ActorDetail;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface ActorService {

    @GET("/3/person/{id}?api_key=b4af86d450ee7b94546e7fc869efeb9f")
    void getActor(@Path("id") int id, Callback<ActorDetail> callback);

}
