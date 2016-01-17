package hr.fer.dm.dm_app3.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

import java.util.List;

import hr.fer.dm.dm_app3.ListViewItems.RolesAdapter;
import hr.fer.dm.dm_app3.Listeners.HidingScrollListener;
import hr.fer.dm.dm_app3.Models.actor.RolesList;
import hr.fer.dm.dm_app3.Models.themoviedb.MovieMinified;
import hr.fer.dm.dm_app3.Network.ApiActorManager;
import hr.fer.dm.dm_app3.R;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Valentino on 12.1.2016..
 */
public class RolesActivity extends AppCompatActivity{

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roles);

        int ajDI = (int) getIntent().getIntExtra(LoginActivity.ACTOR_DETAIL_KEY, 9576);
        final String actorName = (String) getIntent().getExtras().get("ActorName") + "'s roles";

        ApiActorManager.getService().getRoles(ajDI, new Callback<RolesList>() {
            @Override
            public void success(RolesList rolesList, Response response) {

                List<MovieMinified> aMovies = rolesList.getCast();

                initToolbar(actorName);
                initRecyclerView(aMovies);

            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(RolesActivity.this, "Something happened :(", Toast.LENGTH_LONG).show();
            }
        });

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
            Intent intent = new Intent(RolesActivity.this, HomeActivity.class);
            startActivity(intent);
        }
        else if (id == android.R.id.home){
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initToolbar(String toolbarText) {
        mToolbar = (Toolbar) findViewById(R.id.toolbarRoles);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(toolbarText);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.text_icons));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initRecyclerView(List<MovieMinified> movies) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewRoles);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final RolesAdapter recyclerAdapter = new RolesAdapter(movies);
        recyclerView.setAdapter(recyclerAdapter);

        final GestureDetector mGestureDetector = new GestureDetector(RolesActivity.this, new GestureDetector.SimpleOnGestureListener() {

            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());


                if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {

                    MovieMinified movieMinified = (MovieMinified)recyclerAdapter.getItem(recyclerView.getChildAdapterPosition(child) - 1);
                    Intent intent = new Intent(RolesActivity.this, MovieDetailsActivity.class);
                    intent.putExtra("Id", movieMinified.getId());
                    startActivity(intent);

                    return true;

                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        recyclerView.setOnScrollListener(new HidingScrollListener() {
            @Override
            public void onHide() {
                hideViews();
            }

            @Override
            public void onShow() {
                showViews();
            }
        });

    }

    private void hideViews() {
        mToolbar.animate().translationY(-mToolbar.getHeight()).setInterpolator(new AccelerateInterpolator(2));
    }

    private void showViews() {
        mToolbar.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
    }

}
