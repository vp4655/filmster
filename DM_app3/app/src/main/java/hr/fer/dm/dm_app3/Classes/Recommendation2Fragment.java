package hr.fer.dm.dm_app3.Classes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import hr.fer.dm.dm_app3.Activites.MovieDetailsActivity;
import hr.fer.dm.dm_app3.Models.genres.Genre;
import hr.fer.dm.dm_app3.Models.genres.Genredx;
import hr.fer.dm.dm_app3.Models.themoviedb.Movie;
import hr.fer.dm.dm_app3.Models.themoviedb.MovieAdapterRV;
import hr.fer.dm.dm_app3.Models.themoviedb.MovieAdapter_themovie;
import hr.fer.dm.dm_app3.R;
import hr.fer.dm.dm_app3.Models.themoviedb.Moviedx;
import hr.fer.dm.dm_app3.Network.ApiManager;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Kajkara on 9.12.2015..
 */
public class Recommendation2Fragment extends BaseFragment
{


    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Recommendation2Fragment newInstance(int sectionNumber) {
        Recommendation2Fragment fragment = new Recommendation2Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        movieList = new ArrayList<Movie>();

        recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_home_recomm2, container, false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnScrollListener(rvScrollListener);

        getGenres();

        return recyclerView;
    }

    @Override
    protected void getService() {
        ApiManager.getService().getMovies(callback);
    }

    @Override
    protected void getServiceLazy(int page) {
        ApiManager.getService().getMovies(currentPage, callbackLazy);
    }

//    @Override
//    public void getMovies() {
//        pDialog = new ProgressDialog(getActivity());
//        // Showing progress dialog before making http request
//        pDialog.setMessage("Loading...");
//        pDialog.show();
//
//        ApiManager.getService().getMovies(new Callback<Moviedx>() {
//            @Override
//            public void success(Moviedx m, Response response) {
//                try {
//                    hidePDialog();
//                    totalPages = m.getTotalPages();
//                    movieList = m.getMovieList(genres);
//                    recyclerAdapter = new MovieAdapterRV(movieList);
//                    recyclerView.setAdapter(recyclerAdapter);
//                } catch (Exception exc) {
//
//                }
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                hidePDialog();
//                Toast.makeText(getActivity(), "Something happened :(", Toast.LENGTH_LONG).show();
//            }
//        });
//    }

//    @Override
//    public void getMoviesLazy(int page) {
//        getting=true;
//        ApiManager.getService().getMovies(currentPage, new Callback<Moviedx>() {
//            @Override
//            public void success(Moviedx m, Response response) {
//                try {
////                    hidePDialog();
//                    List<Movie> list = m.getMovieList(genres);
//                    recyclerAdapter.addMovies(list);
//
//                    getting=false;
//
//                } catch (Exception exc) {
//                    int a = 0;
//                }
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
////                hidePDialog();
//                Toast.makeText(getActivity(), "Something happened :(", Toast.LENGTH_LONG).show();
//            }
//        });
//    }



}

