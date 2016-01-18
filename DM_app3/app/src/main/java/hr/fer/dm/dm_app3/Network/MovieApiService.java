package hr.fer.dm.dm_app3.Network;

import hr.fer.dm.dm_app3.Models.api.MovieApi;
import hr.fer.dm.dm_app3.Models.api.MoviedxApi;
import hr.fer.dm.dm_app3.Models.genres.Genredx;
import hr.fer.dm.dm_app3.Models.login.LoginResponse;
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

/**
 * Created by Kajkara on 18.12.2015..
 */
public interface MovieApiService {

    @GET("/v1/auth/social?type=facebook")
    void getFToken( @Query("access_token") String token,
                    Callback<LoginResponse> callback);

    @GET("/v1/movies")
    void getMovies( @Query("access_token") String movies, @Query("page") int page, Callback<MoviedxApi> callback);

//    @GET("/v1/movies")
//    void getMoviesSearch( @Query("access_token") String movies, @Query("sort") String sort, @Query("page") int page, @Query("where") String where, Callback<MoviedxApi> callback);

    @GET("/v1/movies")
    void getMoviesSearch( @Query("access_token") String movies, @Query("page") int page, @Query("where") String where, Callback<MoviedxApi> callback);



}
