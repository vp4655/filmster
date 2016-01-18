package hr.fer.dm.dm_app3.Activites;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.squareup.picasso.Picasso;

import java.util.List;

import hr.fer.dm.dm_app3.ImageTransformations.CircleTransformation;
import hr.fer.dm.dm_app3.Models.actor.ActorDetail;
import hr.fer.dm.dm_app3.Models.actor.ActorMinified;
import hr.fer.dm.dm_app3.Models.actor.RolesList;
import hr.fer.dm.dm_app3.Models.themoviedb.MovieDetail;
import hr.fer.dm.dm_app3.Models.themoviedb.MovieMinified;
import hr.fer.dm.dm_app3.Network.ApiActorManager;
import hr.fer.dm.dm_app3.R;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ActorDetailActivity extends AppCompatActivity {

    private String firstName;
    private String uri;
    private String lastName;
    private String email;
    private ImageView ivProfilePicture;
    private TextView name;
    private TextView date;
    private TextView placeOfBirth;
    private TextView popularity;
    private ExpandableTextView etvBiography;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private AppBarLayout appBarLayout;
    private ImageView homeIcon;
    private ImageView dateIcon;
    private TextView tvRoles;
    private Drawer drawer;
    private AccountHeader headerResult;

    private ImageView firstIV;
    private TextView firstTV;
    private ImageView secondIV;
    private TextView secondTV;
    private ImageView thirdIV;
    private TextView thirdTV;
    private ImageView fourthIV;
    private TextView fourthTV;
    private ImageView fifthIV;
    private TextView fifthTV;
    private ImageView sixthIV;
    private TextView sixthTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor_detail);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        collapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.text_icons));

        //TODO otkirti kako promjeniti boju menu-a i back button-a

        ivProfilePicture = (ImageView) findViewById(R.id.ivProfileImage);
        name = (TextView) findViewById(R.id.tvName);
        date = (TextView) findViewById(R.id.tvDate);
        placeOfBirth = (TextView) findViewById(R.id.tvPlaceOfBirth);
        popularity = (TextView) findViewById(R.id.tvPopularity);
        etvBiography = (ExpandableTextView) findViewById(R.id.tvBiography);

        firstTV = (TextView) findViewById(R.id.aFirstName);
        firstIV = (ImageView) findViewById(R.id.aFirstImage);
        secondIV = (ImageView) findViewById(R.id.aSecondImage);
        secondTV = (TextView) findViewById(R.id.aSecondName);
        thirdTV = (TextView) findViewById(R.id.aThirdName);
        thirdIV = (ImageView) findViewById(R.id.aThirdImage);
        fourthIV = (ImageView) findViewById(R.id.aFourthImage);
        fourthTV = (TextView) findViewById(R.id.aFourthName);
        fifthIV = (ImageView) findViewById(R.id.aFifthImage);
        fifthTV = (TextView) findViewById(R.id.aFifthName);
        sixthTV = (TextView) findViewById(R.id.aSixthName);
        sixthIV = (ImageView) findViewById(R.id.aSixthImage);

        homeIcon = (ImageView) findViewById(R.id.homeIcon);
        dateIcon = (ImageView) findViewById(R.id.dateIcon);
        tvRoles = (TextView) findViewById(R.id.roles_text);

        homeIcon.setColorFilter(Color.parseColor("#757575"));
        dateIcon.setColorFilter(Color.parseColor("#757575"));

        final int ajDI = (int) getIntent().getIntExtra(LoginActivity.ACTOR_DETAIL_KEY, 9576);

        tvRoles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ActorDetailActivity.this, RolesActivity.class);
                intent.putExtra(LoginActivity.ACTOR_DETAIL_KEY, ajDI);
                intent.putExtra("ActorName", name.getText());
                startActivity(intent);

            }
        });

        loadActor(ajDI);
    }

    public void loadActor(int id){

        //setTitle(actor.getName());
        collapsingToolbar.setTitle("");

        ApiActorManager.getService().getActor(id, new Callback<ActorDetail>() {
            @Override
            public void success(ActorDetail actorDetail, Response response) {

                final ActorDetail actor = actorDetail;

                try {

                    appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
                    appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                        boolean isShow = false;
                        int scrollRange = -1;

                        @Override
                        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                            if (scrollRange == -1) {
                                scrollRange = appBarLayout.getTotalScrollRange();
                            }
                            if (scrollRange + verticalOffset == 0) {
                                collapsingToolbar.setTitle(actor.getName());
                                isShow = true;
                            } else if (isShow) {
                                collapsingToolbar.setTitle("");
                                isShow = false;
                            }
                        }
                    });

                    name.setText(actor.getName());
                    date.setText(actor.getBirthday() + " - " + actor.getDeathday());
                    placeOfBirth.setText(actor.getPlace_of_birth());
                    popularity.setText(Integer.toString(Math.round(actor.getPopularity())));
                    etvBiography.setText(actor.getBiography());

                    Picasso.with(getApplicationContext()).load(actor.getImage()).transform(new CircleTransformation()).placeholder(R.drawable.person_placeholder).into(ivProfilePicture);

                    ivProfilePicture.setOnClickListener(imageAsLinkListener(actor.getHomepage(), actor.getName()));

                    setRoles(actor.getId());

                } catch (Exception e) {

                }

            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(ActorDetailActivity.this, "Something happened :(", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setRoles(int ajDi){

        ApiActorManager.getService().getRoles(ajDi, new Callback<RolesList>() {
            @Override
            public void success(RolesList rolesList, Response response) {

                List<MovieMinified> aMovies = rolesList.getSmallRoles();

                if(aMovies.size() > 0){
                    MovieMinified a1 = aMovies.get(0);
                    firstTV.setText(a1.getTitle());
                    Picasso.with(getApplicationContext()).load(a1.getPosterPictureUrl()).placeholder(R.drawable.person_placeholder).into(firstIV);
                    RelativeLayout rl1 = (RelativeLayout) findViewById(R.id.aFirstLayout);
                    rl1.setOnClickListener(setMovieDetailsListener(a1.getId()));
                }

                if(aMovies.size() > 1){
                    MovieMinified a2 = aMovies.get(1);
                    secondTV.setText(a2.getTitle());
                    Picasso.with(getApplicationContext()).load(a2.getPosterPictureUrl()).placeholder(R.drawable.person_placeholder).into(secondIV);
                    RelativeLayout rl2 = (RelativeLayout) findViewById(R.id.aSecondLayout);
                    rl2.setOnClickListener(setMovieDetailsListener(a2.getId()));
                }

                if(aMovies.size() > 2){
                    MovieMinified a3 = aMovies.get(2);
                    thirdTV.setText(a3.getTitle());
                    Picasso.with(getApplicationContext()).load(a3.getPosterPictureUrl()).placeholder(R.drawable.person_placeholder).into(thirdIV);
                    RelativeLayout rl3 = (RelativeLayout) findViewById(R.id.aThirdLayout);
                    rl3.setOnClickListener(setMovieDetailsListener(a3.getId()));
                }

                if(aMovies.size() > 3){
                    MovieMinified a4 = aMovies.get(3);
                    fourthTV.setText(a4.getTitle());
                    Picasso.with(getApplicationContext()).load(a4.getPosterPictureUrl()).placeholder(R.drawable.person_placeholder).into(fourthIV);
                    RelativeLayout rl4 = (RelativeLayout) findViewById(R.id.aFourthLayout);
                    rl4.setOnClickListener(setMovieDetailsListener(a4.getId()));
                }

                if(aMovies.size() > 4){
                    MovieMinified a5 = aMovies.get(4);
                    fifthTV.setText(a5.getTitle());
                    Picasso.with(getApplicationContext()).load(a5.getPosterPictureUrl()).placeholder(R.drawable.person_placeholder).into(fifthIV);
                    RelativeLayout rl5 = (RelativeLayout) findViewById(R.id.aFifthLayout);
                    rl5.setOnClickListener(setMovieDetailsListener(a5.getId()));
                }

                if(aMovies.size() > 5){
                    MovieMinified a6 = aMovies.get(5);
                    sixthTV.setText(a6.getTitle());
                    Picasso.with(getApplicationContext()).load(a6.getPosterPictureUrl()).placeholder(R.drawable.person_placeholder).into(sixthIV);
                    RelativeLayout rl6 = (RelativeLayout) findViewById(R.id.aSixthLayout);
                    rl6.setOnClickListener(setMovieDetailsListener(a6.getId()));
                }

            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(ActorDetailActivity.this, "Something happened :(", Toast.LENGTH_LONG).show();
            }
        });

    }

    private View.OnClickListener setMovieDetailsListener(final int actorId){

        return new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActorDetailActivity.this, MovieDetailsActivity.class);
                intent.putExtra("Id", actorId);
                startActivity(intent);
            }

        };

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
            Intent intent = new Intent(ActorDetailActivity.this, HomeActivity.class);
            startActivity(intent);
        }
        else if (id == android.R.id.home){
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private View.OnClickListener imageAsLinkListener(String url, String actorName){
        final Uri uri;

        if(url != null && !url.isEmpty()){
            uri = Uri.parse(url);
        }
        else {
            uri = Uri.parse("https://www.google.hr/search?q=" + actorName.replaceAll(" ", "+"));
        }

        return new View.OnClickListener(){
            Uri myUri = uri;

            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_VIEW, myUri);
                startActivity(intent);
            }

        };


    }

}
