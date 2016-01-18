package hr.fer.dm.dm_app3.Activites;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import hr.fer.dm.dm_app3.Listeners.HidingScrollListener;
import hr.fer.dm.dm_app3.Models.ErrorModel;
import hr.fer.dm.dm_app3.Models.api.MovieAdapterApi;
import hr.fer.dm.dm_app3.Models.api.MovieAdapterHeader;
import hr.fer.dm.dm_app3.Models.api.MovieApi;
import hr.fer.dm.dm_app3.Models.api.MoviedxApi;
import hr.fer.dm.dm_app3.Network.ApiManagerMovie;
import hr.fer.dm.dm_app3.R;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SearchMoviesActivity extends AppCompatActivity {

    protected ProgressDialog pDialog;
    private Toolbar mToolbar;

    int threshold;
    int currentPage;
    int currentPageApi;  // API od 0 ide!
    int totalPages;
    String token;
    String query_;
    String queryInput;
    boolean end;
    protected List<MovieApi> movieListApi;

    @Bind(R.id.searchMovies) SearchView searchView;

    @Bind(R.id.rvMoviesSearch)RecyclerView recyclerViewApi;

    protected  boolean gettingApi = false;
    protected MovieAdapterHeader recyclerAdapterApi;
    protected RecyclerView.OnScrollListener rvScrollListenerApi;
    protected Callback<hr.fer.dm.dm_app3.Models.api.MoviedxApi> callbackApi;
    protected Callback<hr.fer.dm.dm_app3.Models.api.MoviedxApi> callbackLazyApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movies);
        ButterKnife.bind(this);

        mToolbar = (Toolbar) findViewById(R.id.toolbarSearch);
        setSupportActionBar(mToolbar);

        threshold = 10;
        currentPage = 1; // imdb broji od 1!!!
        currentPageApi=0;
        totalPages = 0;
        token="";
        end = false;

        movieListApi = new ArrayList<>();

        recyclerViewApi = (RecyclerView) findViewById(R.id.rvMoviesSearch);
        recyclerViewApi.setLayoutManager(new LinearLayoutManager(this));

        final GestureDetector mGestureDetector = new GestureDetector(SearchMoviesActivity.this, new GestureDetector.SimpleOnGestureListener() {

            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });

        recyclerViewApi.setOnScrollListener(new HidingScrollListener() {
            @Override
            public void onHide() {
                hideViews();
            }

            @Override
            public void onShow() {
                showViews();
            }
        });

        rvScrollListenerApi = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!gettingApi) {
                    LinearLayoutManager layoutManager = ((LinearLayoutManager) recyclerView.getLayoutManager());
                    int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
                    int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();

                    int count = movieListApi.size();
                    int diff = count - 1 - threshold;
                    if ((firstVisiblePosition >= count - 1 - threshold) && !end) {
                        currentPageApi++;
                        if(!end)
                        {
                            ApiManagerMovie.getService().getMoviesSearch(token ,"popularity",currentPageApi, query_, callbackLazyApi);
                        }
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
        recyclerViewApi.addOnScrollListener(rvScrollListenerApi);

        ///////////////////API//////////////////////
        callbackApi = new Callback<MoviedxApi>() {
            @Override
            public void success(MoviedxApi m, Response response) {
                try {
                    hidePDialog();
                    movieListApi = m.getMovieList();

                    int a = m.getMovieList().size();
                    int b = m.getlimit();
                    if(m.getMovieList().size()<m.getlimit())
                        end = true;

                    recyclerAdapterApi = new MovieAdapterHeader(movieListApi);
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
                //Toast.makeText(SearchMoviesActivity.this, errorMess, Toast.LENGTH_LONG).show();

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
                Toast.makeText(getApplicationContext(), errorMess, Toast.LENGTH_LONG).show();
            }
        };



        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                movieListApi.clear();

                queryInput = query;
                //Toast.makeText(SearchMoviesActivity.this, "Query: " + query, Toast.LENGTH_LONG).show();

                String s = getResources().getString(R.string.sharedPref);
                SharedPreferences sp = SearchMoviesActivity.this.getSharedPreferences(s, Activity.MODE_PRIVATE);
                token= sp.getString("token", "");

                query_= "{\"title\":{\"contains\":\""+ query +"\"}}";
                pDialog = new ProgressDialog(SearchMoviesActivity.this);
                // Showing progress dialog before making http request
                pDialog.setMessage("Loading...");
                pDialog.show();
                ApiManagerMovie.getService().getMoviesSearch(token,"popularity",currentPageApi, query_, callbackApi);



                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        //changeSearchViewTextColor(searchView);
        ImageView closeBtn = (ImageView) findViewById(R.id.search_close_btn);

        searchView.requestFocus();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);


        //searchView.setBackgroundColor(getResources().getColor(R.color.gold));
//        View v = searchView.findViewById(android.support.v7.appcompat.R.id.search_plate);
//        v.setBackgroundColor(getResources().getColor(R.color.gold));

//        // Getting id for 'search_plate' - the id is part of generate R file,
//        // so we have to get id on runtime.
//        int searchPlateId = searchView.getContext().getResources().getIdentifier("android:id/search_plate", null, null);
//        // Getting the 'search_plate' LinearLayout.
//        View searchPlate = searchView.findViewById(searchPlateId);
//        // Setting background of 'search_plate' to earlier defined drawable.
//        searchPlate.setBackgroundResource(R.drawable.textfield_searchview_holo_light);

        int searchPlateId = searchView.getContext().getResources()
                .getIdentifier("android:id/search_plate", null, null);
        View searchPlateView = searchView.findViewById(searchPlateId);
        if (searchPlateView != null) {
            searchPlateView.setBackgroundColor(Color.WHITE); //depand you can set
        }
    }

    private void hideViews() {
        mToolbar.animate().translationY(-mToolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));
        searchView.animate().translationY(-mToolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));
    }

    private void showViews() {
        mToolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
        searchView.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
    }

    private void changeSearchViewTextColor(View view) {
        if (view != null) {
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(Color.WHITE);
                return;
            } else if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    changeSearchViewTextColor(viewGroup.getChildAt(i));
                }
            }
        }
    }

    protected void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }




    protected void getMovies() {
        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        //ApiManager.getService().getMovies(callback);
        getService();
    }


    protected void getMoviesLazy(int page) {
        gettingApi=true;
        getServiceLazy(page);
    }


    protected void getService() {
        ApiManagerMovie.getService().getMovies(token, currentPageApi, callbackApi);
    }


    protected void getServiceLazy(int page) {
        ApiManagerMovie.getService().getMovies(token, currentPageApi, callbackLazyApi);
    }


}
