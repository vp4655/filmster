package hr.fer.dm.dm_app3.Network;

import hr.fer.dm.dm_app3.Models.actor.ActorDetail;
import hr.fer.dm.dm_app3.Models.actor.ActorWrapper;
import hr.fer.dm.dm_app3.Models.actor.RolesList;
import hr.fer.dm.dm_app3.Models.themoviedb.MovieMinified;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface ActorService {

    @GET("/3/person/{id}/movie_credits?api_key=b4af86d450ee7b94546e7fc869efeb9f")
    void getRoles(@Path("id") int id, Callback<RolesList> callback);

}
