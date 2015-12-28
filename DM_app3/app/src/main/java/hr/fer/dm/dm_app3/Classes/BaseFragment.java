package hr.fer.dm.dm_app3.Classes;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import hr.fer.dm.dm_app3.ListViewItems.Movie;

/**
 * Created by Kajkara on 10.12.2015..
 */
public abstract class BaseFragment extends Fragment{
    protected ProgressDialog pDialog;
    protected String url;

    protected List<Movie> movieList = new ArrayList<Movie>();

    int threshold;
    int currentPage;
    int totalPages;

    public BaseFragment()
    {
        threshold = 10;
        currentPage = 1; // imdb broji od 1!!!
        totalPages = 0;
    }

    public abstract void getMovies();

    public abstract void getMoviesLazy(int page);


    protected void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }


}
