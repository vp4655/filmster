package hr.fer.dm.dm_app3.Network;

import hr.fer.dm.dm_app3.Models.themoviedb.Moviedx;
import hr.fer.dm.dm_app3.Models.themoviedb.Sprite;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Kajkara on 18.12.2015..
 */
public interface MovieService {

    @GET("/json/movies.json")
    void getMovies_androidhive(Callback<Moviedx> callback);

    @GET("/3/movie/popular?api_key=b4af86d450ee7b94546e7fc869efeb9f")
    void getMovies(Callback<Moviedx> callback);

//    @GET("/3/movie/popular?api_key=b4af86d450ee7b94546e7fc869efeb9f")
//    void getMovies(@Header("page") int page, Callback<Moviedx> callback);

//    @GET("/3/movie/popular?api_key=b4af86d450ee7b94546e7fc869efeb9f&page={page_num}")
//    void getMovies(@Path("page_num") int page, Callback<Moviedx> callback);

    @GET("/3/movie/popular?api_key=b4af86d450ee7b94546e7fc869efeb9f")
    void getMovies(@Query("page") int page, Callback<Moviedx> callback);
}
