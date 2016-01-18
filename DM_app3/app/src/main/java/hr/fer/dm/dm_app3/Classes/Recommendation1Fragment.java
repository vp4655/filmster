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
import java.util.List;

import hr.fer.dm.dm_app3.Models.api.MovieApi;
import hr.fer.dm.dm_app3.Network.ApiManagerMovie;
import hr.fer.dm.dm_app3.R;

/**
 * Created by Kajkara on 9.12.2015..
 */
public class Recommendation1Fragment extends BaseFragment {
    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */

    public static Recommendation1Fragment newInstance(int sectionNumber) {
        Recommendation1Fragment fragment = new Recommendation1Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public Recommendation1Fragment() {
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
        token= sp.getString("token", "");

        getMovies();
        return recyclerViewApi;
    }


    @Override
    protected void getService() {
        ApiManagerMovie.getService().getMovies(token, currentPageApi, callbackApi);
    }

    @Override
    protected void getServiceLazy(int page) {
        ApiManagerMovie.getService().getMovies(token, currentPageApi, callbackLazyApi);
    }

}