package hr.fer.dm.dm_app3.Network;

import hr.fer.dm.dm_app3.Models.actor.ActorWrapper;
import hr.fer.dm.dm_app3.Models.actor.CastList;
import hr.fer.dm.dm_app3.Models.actor.CastWrapper;
import hr.fer.dm.dm_app3.Models.actor.CrewList;
import hr.fer.dm.dm_app3.Models.actor.CrewWrapper;
import hr.fer.dm.dm_app3.Models.api.MovieApi;
import hr.fer.dm.dm_app3.Models.api.MoviedxApi;
import hr.fer.dm.dm_app3.Models.genres.Genredx;
import hr.fer.dm.dm_app3.Models.login.LoginResponse;
import hr.fer.dm.dm_app3.Models.themoviedb.ApiWraper;
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

public interface MovieApiService {

    @GET("/v1/auth/social?type=facebook")
    void getFToken( @Query("access_token") String token,
                    Callback<LoginResponse> callback);

    @GET("/v1/movies")
    void getMovies( @Query("access_token") String movies, @Query("page") int page, Callback<MoviedxApi> callback);

    @GET("/v1/movies/{id}")
    void getMovie(@Query("access_token") String movies, @Query("fields") String fields, @Path("id") int id, Callback<ApiWraper> callback);

    @GET("/v1/movies/{id}")
    void getCast(@Query("access_token") String movies, @Query("fields") String fields, @Path("id") int id, Callback<CastWrapper> callback);

    @GET("/v1/movies/{id}")
    void getCrew(@Query("access_token") String movies, @Query("fields") String fields, @Path("id") int id, Callback<CrewWrapper> callback);

    @GET("/v1/actors/{id}")
    void getActor(@Query("access_token") String movies, @Query("fields") String fields, @Path("id") int id, Callback<ActorWrapper> callback);

}
