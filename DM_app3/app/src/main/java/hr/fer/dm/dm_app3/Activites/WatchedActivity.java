package hr.fer.dm.dm_app3.Activites;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
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

public class WatchedActivity extends AppCompatActivity {

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

    @Bind(R.id.rvMoviesWatched)RecyclerView recyclerViewApi;                // <--

    protected  boolean gettingApi = false;
    protected MovieAdapterHeader recyclerAdapterApi;
    protected RecyclerView.OnScrollListener rvScrollListenerApi;
    protected Callback<hr.fer.dm.dm_app3.Models.api.MoviedxApi> callbackApi;
    protected Callback<hr.fer.dm.dm_app3.Models.api.MoviedxApi> callbackLazyApi;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watched);              // <--
        ButterKnife.bind(this);

        threshold = 10;
        currentPageApi=0;
        totalPages = 0;
        token="";
        end = false;

        movieListApi = new ArrayList<>();

        mToolbar = (Toolbar) findViewById(R.id.toolbarWatched);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(getString(R.string.cast_string));
        mToolbar.setTitleTextColor(getResources().getColor(R.color.text_icons));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerViewApi = (RecyclerView) findViewById(R.id.rvMoviesWatched);            // <--
        recyclerViewApi.setLayoutManager(new LinearLayoutManager(this));

        final GestureDetector mGestureDetector = new GestureDetector(WatchedActivity.this, new GestureDetector.SimpleOnGestureListener() {

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
                            ApiManagerMovie.getService().getMoviesWatched(token,currentPageApi,callbackLazyApi);            //<--
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
        SharedPreferences sp = WatchedActivity.this.getSharedPreferences(s, Activity.MODE_PRIVATE);
        token= sp.getString("token", "");

        pDialog = new ProgressDialog(WatchedActivity.this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();
        ApiManagerMovie.getService().getMoviesWatched(token, currentPageApi, callbackApi);

    }

    protected void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_other, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_other) {
            Intent intent = new Intent(WatchedActivity.this, HomeActivity.class);
            startActivity(intent);
        }
        else if (id == android.R.id.home){
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
