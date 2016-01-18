package hr.fer.dm.dm_app3.Classes;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

import hr.fer.dm.dm_app3.Activites.HomeActivity;
import hr.fer.dm.dm_app3.Models.themoviedb.Movie;
import hr.fer.dm.dm_app3.R;
import hr.fer.dm.dm_app3.Network.ApiManager;

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


        HomeActivity activity = (HomeActivity) getActivity();
        activity.setF3((BaseFragment)this);

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

}

