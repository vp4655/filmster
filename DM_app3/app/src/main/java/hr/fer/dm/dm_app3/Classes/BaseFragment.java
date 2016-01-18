package hr.fer.dm.dm_app3.Classes;

import android.app.Activity;
import android.app.ProgressDialog;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.AbsListView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import hr.fer.dm.dm_app3.Activites.HomeActivity;
import hr.fer.dm.dm_app3.Classes.Fab.FilterOptions;
import hr.fer.dm.dm_app3.Models.ErrorModel;
import hr.fer.dm.dm_app3.Models.api.MovieAdapterApi;
import hr.fer.dm.dm_app3.Models.api.MovieApi;
import hr.fer.dm.dm_app3.Models.api.MoviedxApi;
import hr.fer.dm.dm_app3.Models.genres.Genre;
import hr.fer.dm.dm_app3.Models.genres.Genredx;
import hr.fer.dm.dm_app3.Models.themoviedb.Movie;
import hr.fer.dm.dm_app3.Models.themoviedb.MovieAdapterRV;
import hr.fer.dm.dm_app3.Models.themoviedb.Moviedx;
import hr.fer.dm.dm_app3.Network.ApiManager;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by Kajkara on 10.12.2015..
 */
public abstract class BaseFragment extends Fragment{

    protected ProgressDialog pDialog;
    protected FilterOptions filterOptions;
    //protected String url;

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    protected static final String ARG_SECTION_NUMBER = "section_number";

    int threshold;
    int currentPage;
    int currentPageApi;  // API od 0 ide!
    int totalPages;

    protected List<Movie> movieList;

    protected List<hr.fer.dm.dm_app3.Models.api.MovieApi> movieListApi;

    // TODO: obrisati poslije - genreova ne dohvaća app
    HashMap<Integer, String> genres = new HashMap<Integer, String>();

    protected RecyclerView recyclerView;
    protected  boolean getting = false;

    protected RecyclerView.OnScrollListener rvScrollListener;
    //private MovieAdapter_themovie adapter;
    protected MovieAdapterRV recyclerAdapter;


    protected Callback<Moviedx> callback;
    protected Callback<Moviedx> callbackLazy;

    // API

    protected String token;
    protected Boolean end;

    protected RecyclerView recyclerViewApi;
    protected  boolean gettingApi = false;
    protected MovieAdapterApi recyclerAdapterApi;
    protected RecyclerView.OnScrollListener rvScrollListenerApi;
    protected Callback<hr.fer.dm.dm_app3.Models.api.MoviedxApi> callbackApi;
    protected Callback<hr.fer.dm.dm_app3.Models.api.MoviedxApi> callbackLazyApi;

    public BaseFragment()
    {
        threshold = 10;
        currentPage = 1; // imdb broji od 1!!!
        currentPageApi=0;
        totalPages = 0;
        token="";
        end = false;


        //TODO: maknuti ih poslije
        rvScrollListener = new RecyclerView.OnScrollListener() {
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

        };
        callback = new Callback<Moviedx>() {
            @Override
            public void success(Moviedx m, Response response) {
                try {
                    hidePDialog();
                    totalPages = m.getTotalPages();
                    movieList = m.getMovieList(genres);
                    recyclerAdapter = new MovieAdapterRV(movieList);
                    recyclerView.setAdapter(recyclerAdapter);
                } catch (Exception exc) {

                }
            }

            @Override
            public void failure(RetrofitError error) {
                hidePDialog();
                String er= error.getUrl();

                String errorMess = ":(  ";
                if (error.getResponse() != null) {
                    ErrorModel errorModel = (ErrorModel) error.getBodyAs(ErrorModel.class);
                    errorMess+=errorModel.getCode()+": "+ errorModel.getMessage();
                }
                Toast.makeText(getActivity(), errorMess, Toast.LENGTH_LONG).show();
            }
        };
        callbackLazy = new Callback<Moviedx>() {
            @Override
            public void success(Moviedx m, Response response) {
                try {
//                    hidePDialog();
                    List<Movie> list = m.getMovieList(genres);
                    recyclerAdapter.addMovies(list);

                    getting = false;

                } catch (Exception exc) {
                    int a = 0;
                }
            }

            @Override
            public void failure(RetrofitError error) {
                hidePDialog();
                String er= error.getUrl();

                String errorMess = ":(  ";
                if (error.getResponse() != null) {
                    ErrorModel errorModel = (ErrorModel) error.getBodyAs(ErrorModel.class);
                    errorMess+=errorModel.getCode()+": "+ errorModel.getMessage();
                }
                Toast.makeText(getActivity(), errorMess, Toast.LENGTH_LONG).show();
            }
        };




        ///////////////////API//////////////////////
        callbackApi = new Callback<MoviedxApi>() {
            @Override
            public void success(MoviedxApi m, Response response) {
                try {
                    hidePDialog();
                    movieListApi = m.getMovieList();

                    if(m.getMovieList().size()<m.getlimit())
                        end = true;

                    recyclerAdapterApi = new MovieAdapterApi(movieListApi);
                    recyclerViewApi.setAdapter(recyclerAdapterApi);
                } catch (Exception exc) {

                }
            }

            @Override
            public void failure(RetrofitError error) {
                hidePDialog();
                String er= error.getUrl();

                String errorMess = ":(  ";
                if (error.getResponse() != null) {
                    ErrorModel errorModel = (ErrorModel) error.getBodyAs(ErrorModel.class);
                    errorMess+=errorModel.getCode()+": "+ errorModel.getMessage();
                }
                Toast.makeText(getActivity(), errorMess, Toast.LENGTH_LONG).show();

            }
        };

        callbackLazyApi = new Callback<MoviedxApi>() {
            @Override
            public void success(MoviedxApi m, Response response) {
                try {
//                    hidePDialog();
                    List<MovieApi> list = m.getMovieList();
                    recyclerAdapterApi.addMovies(list);

                    if(m.getMovieList().size()<m.getlimit())
                        end = true;
                    gettingApi = false;

                } catch (Exception exc) {
                    int a = 0;
                }
            }

            @Override
            public void failure(RetrofitError error) {
                //hidePDialog();
                String er= error.getUrl();

                String errorMess = ":(  ";
                if (error.getResponse() != null) {
                    ErrorModel errorModel = (ErrorModel) error.getBodyAs(ErrorModel.class);
                    errorMess+=errorModel.getCode()+": "+ errorModel.getMessage();
                }
                Toast.makeText(getActivity(), errorMess, Toast.LENGTH_LONG).show();
            }
        };

        rvScrollListenerApi = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!gettingApi) {
                    LinearLayoutManager layoutManager = ((LinearLayoutManager) recyclerView.getLayoutManager());
                    int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
                    int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();

                    int count = movieListApi.size();
                    if ((firstVisiblePosition >= count - 1 - threshold) && !end) {
                        currentPageApi++;
                        if(!end)
                            getMoviesLazy(currentPageApi);
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

        };
    }




    @Override
    public void onResume() {
        super.onResume();

        // reload only if filter is cahanged
//        if(isChanged)
//        {
//            reInit();
//        }
        //if(((HomeActivity)getActivity()).filterOptions.filter())
        //Toast.makeText(getActivity(), "Resume", Toast.LENGTH_LONG).show();
    }

    public void reInit()
    {
        movieListApi.clear();
    }

    //public abstract void getMovies();

//    public abstract void getMoviesLazy(int page);


    protected void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    protected void getMovies() {
        pDialog = new ProgressDialog(getActivity());
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        //ApiManager.getService().getMovies(callback);
        getService();
    }

    protected abstract void getService();

    protected void getMoviesLazy(int page) {
        getting=true;
        gettingApi=true;
        //ApiManager.getService().getMovies(currentPage, callback);
        getServiceLazy(page);
    }

    protected abstract void getServiceLazy(int page);



    protected void getGenres() {

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
                hidePDialog();
                String er= error.getUrl();

                String errorMess = ":(  ";
                if (error.getResponse() != null) {
                    ErrorModel errorModel = (ErrorModel) error.getBodyAs(ErrorModel.class);
                    errorMess+=errorModel.getCode()+": "+ errorModel.getMessage();
                }
                Toast.makeText(getActivity(), errorMess, Toast.LENGTH_LONG).show();

            }
        });
    }

    public void reload()
    {
        Toast.makeText(getActivity(), "FRAGM", Toast.LENGTH_SHORT).show();
        movieListApi.clear();

    }

    public void setFilterOptions(FilterOptions f)
    {
        filterOptions = f;
    }
}

