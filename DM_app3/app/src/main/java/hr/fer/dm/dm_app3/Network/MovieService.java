package hr.fer.dm.dm_app3.Network;

import hr.fer.dm.dm_app3.Models.actor.CastList;
import hr.fer.dm.dm_app3.Models.genres.Genredx;
import hr.fer.dm.dm_app3.Models.themoviedb.Movie;
import hr.fer.dm.dm_app3.Models.themoviedb.MovieDetail;
import hr.fer.dm.dm_app3.Models.themoviedb.Moviedx;
import hr.fer.dm.dm_app3.Models.themoviedb.Sprite;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

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

    @GET("/3/genre/movie/list?api_key=b4af86d450ee7b94546e7fc869efeb9f")
    void getGenres(Callback<Genredx> callback);

    @GET("/3/movie/{id}?api_key=b4af86d450ee7b94546e7fc869efeb9f")
    void getMovie(@Path("id") int id, Callback<MovieDetail> callback);

    @GET("/3/movie/{id}/casts?api_key=b4af86d450ee7b94546e7fc869efeb9f")
    void getCast(@Path("id") int id, Callback<CastList> callback);

}
