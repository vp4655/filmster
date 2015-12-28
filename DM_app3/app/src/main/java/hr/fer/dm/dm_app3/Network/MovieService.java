package hr.fer.dm.dm_app3.Network;

import hr.fer.dm.dm_app3.Models.themoviedb.Moviedx;
import hr.fer.dm.dm_app3.Models.themoviedb.Sprite;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Kajkara on 18.12.2015..
 */
public interface MovieService {

    @GET("/json/movies.json")
    void getMovies_androidhive(Callback<Moviedx> callback);

    @GET("/3/movie/popular?api_key=b4af86d450ee7b94546e7fc869efeb9f")
    void getMovies(Callback<Moviedx> callback);
}
