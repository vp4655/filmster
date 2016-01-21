package hr.fer.dm.dm_app3.Activites;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
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

public class WatchLaterActivity extends AppCompatActivity {

    protected ProgressDialog pDialog;

    int threshold;
    int currentPage;
    int currentPageApi;  // API od 0 ide!
    int totalPages;
    String token;
    String query_;
    String queryInput;
    boolean end;
    protected List<MovieApi> movieListApi;

    @Bind(R.id.rvMoviesWatchLater)RecyclerView recyclerViewApi;                // <--

    protected  boolean gettingApi = false;
    protected MovieAdapterHeader recyclerAdapterApi;
    protected RecyclerView.OnScrollListener rvScrollListenerApi;
    protected Callback<hr.fer.dm.dm_app3.Models.api.MoviedxApi> callbackApi;
    protected Callback<hr.fer.dm.dm_app3.Models.api.MoviedxApi> callbackLazyApi;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_later);              // <--
        ButterKnife.bind(this);

        threshold = 10;
        currentPageApi=0;
        totalPages = 0;
        token="";
        end = false;

        movieListApi = new ArrayList<>();

        mToolbar = (Toolbar) findViewById(R.id.toolbarWatchLater);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(getString(R.string.watchlist));
        mToolbar.setTitleTextColor(getResources().getColor(R.color.text_icons));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerViewApi = (RecyclerView) findViewById(R.id.rvMoviesWatchLater);            // <--
        recyclerViewApi.setLayoutManager(new LinearLayoutManager(this));

        final GestureDetector mGestureDetector = new GestureDetector(WatchLaterActivity.this, new GestureDetector.SimpleOnGestureListener() {

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
                            ApiManagerMovie.getService().getMoviesWatchLater(token, currentPageApi, callbackLazyApi);            //<--
                        }
                    }
                }

            }


            public void onScrollStateChanged(AbsListView view, int scrollState) {

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


        initList();
    }

    private void hideViews() {
        mToolbar.animate().translationY(-mToolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));
    }

    private void showViews() {
        mToolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
    }

    public void initList()
    {
        String s = getResources().getString(R.string.sharedPref);
        SharedPreferences sp = WatchLaterActivity.this.getSharedPreferences(s, Activity.MODE_PRIVATE);
        token= sp.getString("token", "");

        pDialog = new ProgressDialog(WatchLaterActivity.this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();
        ApiManagerMovie.getService().getMoviesWatchLater(token,currentPageApi, callbackApi);

    }

    protected void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

}
