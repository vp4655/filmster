package hr.fer.dm.dm_app3.Activites;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.squareup.picasso.Picasso;

import java.util.List;

import hr.fer.dm.dm_app3.ListViewItems.CrewAdapter;
import hr.fer.dm.dm_app3.Models.actor.ActorMinified;
import hr.fer.dm.dm_app3.ListViewItems.CastAdapter;
import hr.fer.dm.dm_app3.Listeners.HidingScrollListener;
import hr.fer.dm.dm_app3.Models.actor.CastList;
import hr.fer.dm.dm_app3.Models.actor.CrewList;
import hr.fer.dm.dm_app3.Models.actor.CrewMinified;
import hr.fer.dm.dm_app3.Models.actor.CrewWrapper;
import hr.fer.dm.dm_app3.Network.ApiManager;
import hr.fer.dm.dm_app3.Network.ApiManagerMovie;
import hr.fer.dm.dm_app3.R;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CrewActivity extends AppCompatActivity {

    private String name;
    private String uri;
    private String lastName;
    private String email;
    private Toolbar mToolbar;
    private String apiToken;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast);

        int ajDI = (int) getIntent().getExtras().get("Id");

        String s = getResources().getString(R.string.sharedPref);
        SharedPreferences sp = this.getApplicationContext().getSharedPreferences(s, Activity.MODE_PRIVATE);
        apiToken = sp.getString("token", "");

        ApiManagerMovie.getService().getCrew(apiToken, "movieId,crew",ajDI, new Callback<CrewWrapper>() {
            @Override
            public void success(CrewWrapper wrapper, Response response) {

                CrewList castList = wrapper.getCrewList();

                List<CrewMinified> aCrew = castList.getCrew();

                initToolbar();
                initRecyclerView(aCrew);

            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(CrewActivity.this, "Something happened :(", Toast.LENGTH_LONG).show();
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
            Intent intent = new Intent(CrewActivity.this, HomeActivity.class);
            startActivity(intent);
        }
        else if (id == android.R.id.home){
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbarCast);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(getString(R.string.cast_string));
        mToolbar.setTitleTextColor(getResources().getColor(R.color.text_icons));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initRecyclerView(List<CrewMinified> crew) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewCast);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final CrewAdapter recyclerAdapter = new CrewAdapter(crew);
        recyclerView.setAdapter(recyclerAdapter);

        final GestureDetector mGestureDetector = new GestureDetector(CrewActivity.this, new GestureDetector.SimpleOnGestureListener() {

            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());


                if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {

                    CrewMinified crewMinified = (CrewMinified)recyclerAdapter.getItem(recyclerView.getChildAdapterPosition(child) - 1);
                    Intent intent = new Intent(CrewActivity.this, ActorDetailActivity.class);
                    intent.putExtra(LoginActivity.ACTOR_DETAIL_KEY, crewMinified.getId());
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
