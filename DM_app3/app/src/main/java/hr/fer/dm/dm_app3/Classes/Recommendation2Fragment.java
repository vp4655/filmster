package hr.fer.dm.dm_app3.Classes;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

import hr.fer.dm.dm_app3.Activites.HomeActivity;
import hr.fer.dm.dm_app3.Models.api.MovieApi;
import hr.fer.dm.dm_app3.Models.themoviedb.Movie;
import hr.fer.dm.dm_app3.Network.ApiManagerMovie;
import hr.fer.dm.dm_app3.R;
import hr.fer.dm.dm_app3.Network.ApiManager;

/**
 * Created by Kajkara on 9.12.2015..
 */
public class Recommendation2Fragment extends BaseFragment {
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

    public Recommendation2Fragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        movieListApi = new ArrayList<MovieApi>();

        recyclerViewApi = (RecyclerView) inflater.inflate(R.layout.fragment_home_recomm1, container, false);
        recyclerViewApi.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewApi.addOnScrollListener(rvScrollListenerApi);

        String s = getResources().getString(R.string.sharedPref);
        SharedPreferences sp = getContext().getSharedPreferences(s, Activity.MODE_PRIVATE);
        token = sp.getString("token", "");
        if (movieListApi.size() == 0) {
            getMovies();
        }

        HomeActivity activity = (HomeActivity) getActivity();
        activity.setF3((BaseFragment) this);

        return recyclerViewApi;
    }


    @Override
    protected void getService() {
        String s = getWhere();

        if(s != "")
        {
            ApiManagerMovie.getService().getMoviesHotSearch("popularity DESC", currentPageApi, s, token, callbackApi);

        }
        else
        {
            ApiManagerMovie.getService().getMoviesHot("popularity DESC", token, currentPageApi, callbackApi);

        }
    }

    @Override
    protected void getServiceLazy(int page) {
        String s = getWhere();

        if(s != "")
        {
            ApiManagerMovie.getService().getMoviesHotSearch("popularity DESC", currentPageApi, s, token, callbackLazyApi);
        }
        else
        {
            ApiManagerMovie.getService().getMoviesHot("popularity DESC", token, currentPageApi, callbackLazyApi);
        }
    }

}