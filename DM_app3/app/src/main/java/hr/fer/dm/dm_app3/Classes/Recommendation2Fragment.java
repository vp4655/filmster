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
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    //private MovieAdapter_themovie adapter;
    private MovieAdapterRV recyclerAdapter;

    HashMap<Integer, String> genres = new HashMap<Integer, String>();

    private List<hr.fer.dm.dm_app3.Models.themoviedb.Movie> movieList;

    private RecyclerView recyclerView;
    private  boolean getting = false;
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
//            if(movieList.isEmpty()){
//            pDialog = new ProgressDialog(getActivity());
//            // Showing progress dialog before making http request
//            pDialog.setMessage("Loading...");
//            pDialog.show();


//            getMovies();  // u getGenres() jer treba na success toga

//            hidePDialog();
        //}




        recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_home_recomm2, container, false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!getting) {
                    LinearLayoutManager layoutManager = ((LinearLayoutManager) recyclerView.getLayoutManager());
                    int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
                    int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();

                    int count = movieList.size();
                    if ((firstVisiblePosition >= count - 1 - threshold) && totalPages > currentPage) {
                        currentPage++;
                        getMoviesLazy(currentPage);
                    }
                }

            }


            public void onScrollStateChanged(AbsListView view, int scrollState) {


//                    int visible = recyclerView. ();
//                    int count = recyclerView.getCount();
//                    int result = recyclerView.getCount() - 1 - threshold;
//                    if ((recyclerView.getLastVisiblePosition() >= recyclerView.getCount() - 1 - threshold) && totalPages > currentPage) {
//                        currentPage++;
//                        getMoviesLazy(currentPage);
//                    }
            }

        });

        getGenres();
        return recyclerView;
    }


    @Override
    public void getMovies() {
        pDialog = new ProgressDialog(getActivity());
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        ApiManager.getService().getMovies(new Callback<Moviedx>() {
            @Override
            public void success(Moviedx m, Response response) {
                try {
                    hidePDialog();
                    totalPages = m.getTotalPages();

//                    adapter = new MovieAdapter_themovie(getActivity(), m.getMovieList(genres));
//                    listView.setAdapter(adapter);


                    movieList = m.getMovieList(genres);
                    recyclerAdapter = new MovieAdapterRV(movieList);
                    recyclerView.setAdapter(recyclerAdapter);
                } catch (Exception exc) {

                }
            }

            @Override
            public void failure(RetrofitError error) {
                hidePDialog();
                Toast.makeText(getActivity(), "Something happened :(", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void getMoviesLazy(int page) {
//        ne treba loader kad loada unaprijed
//        pDialog = new ProgressDialog(getActivity());
//        // Showing progress dialog before making http request
//        pDialog.setMessage("Loading...");
//        pDialog.show();
        getting=true;
        ApiManager.getService().getMovies(currentPage, new Callback<Moviedx>() {
            @Override
            public void success(Moviedx m, Response response) {
                try {
//                    hidePDialog();
                    recyclerAdapter.addMovies(m.getMovieList(genres));
//                    listView.setAdapter(adapter);
                    recyclerAdapter.notifyDataSetChanged();
                    getting=false;

                } catch (Exception exc) {
                    int a = 0;
                }
            }

            @Override
            public void failure(RetrofitError error) {
//                hidePDialog();
                Toast.makeText(getActivity(), "Something happened :(", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getGenres() {

        ApiManager.getService().getGenres(new Callback<Genredx>() {
            @Override
            public void success(Genredx genredx, Response response) {
                try {
                    List<Genre> genreList = genredx.getGenreList();
                    for (Genre genre : genreList) {
                        genres.put(genre.getId(), genre.getName());
                    }
                    getMovies();
                } catch (Exception exc) {

                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), "Something happened :(", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void init(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {


    }

}

