package hr.fer.dm.dm_app3.Activites;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import hr.fer.dm.dm_app3.Models.actor.ActorMinified;
import hr.fer.dm.dm_app3.Models.actor.CastList;
import hr.fer.dm.dm_app3.Models.actor.CastWrapper;
import hr.fer.dm.dm_app3.Models.themoviedb.ApiWraper;
import hr.fer.dm.dm_app3.Models.themoviedb.MovieDetail;
import hr.fer.dm.dm_app3.Network.ApiManager;
import hr.fer.dm.dm_app3.Network.ApiManagerMovie;
import hr.fer.dm.dm_app3.R;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MovieDetailsActivity extends AppCompatActivity {
    @Bind(R.id.tvOverview) ExpandableTextView overviewTV;
    @Bind(R.id.castText) TextView castTV;
    @Bind(R.id.toolbar_movie) Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar_movie) CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.appbar_movie) AppBarLayout appBarLayout;
    @Bind(R.id.thumbnail_movie) ImageView thumbnailIV;
    @Bind(R.id.crewLink) TextView crewLink;
    @Bind(R.id.firstImage) ImageView firstIV;
    @Bind(R.id.firstName) TextView firstTV;
    @Bind(R.id.secondImage) ImageView secondIV;
    @Bind(R.id.secondName) TextView secondTV;
    @Bind(R.id.thirdImage) ImageView thirdIV;
    @Bind(R.id.thirdName) TextView thirdTV;
    @Bind(R.id.fourthImage) ImageView fourthIV;
    @Bind(R.id.fourthName) TextView fourthTV;
    @Bind(R.id.fifthImage) ImageView fifthIV;
    @Bind(R.id.fifthName) TextView fifthTV;
    @Bind(R.id.sixthImage) ImageView sixthIV;
    @Bind(R.id.sixthName) TextView sixthTV;
    @Bind(R.id.imdbLink) Button imdbButton;
    @Bind(R.id.homepageLink) Button homepageButton;
    @Bind(R.id.share_btn) ShareButton shareButton;
    @Bind(R.id.swiper) SwipeLayout swiper;
    @Bind(R.id.runtime) TextView runtimeTV;
    @Bind(R.id.releaseDate) TextView releaseDateTV;
    @Bind(R.id.tvRottenTomato) TextView tvRottenTomato;
    @Bind(R.id.tvRottenTomatoUser) TextView getTvRottenTomatoUser;
    @Bind(R.id.tvImdb) TextView tvImdb;
    @Bind(R.id.tvMetaCritic) TextView tvMetaCritic;
    @Bind(R.id.directors) TextView directors;

    private Bitmap image;
    private String apiToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.text_icons));
        collapsingToolbar.setExpandedTitleColor(getResources().getColor(R.color.text_icons));

        final int id = (int)getIntent().getExtras().get("Id");
        String s = getResources().getString(R.string.sharedPref);
        SharedPreferences sp = this.getApplicationContext().getSharedPreferences(s, Activity.MODE_PRIVATE);
        apiToken = sp.getString("token", "");

        swiper.setShowMode(SwipeLayout.ShowMode.LayDown);

        setMovie(apiToken, id);

        castTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MovieDetailsActivity.this, CastActivity.class);
                intent.putExtra(LoginActivity.ACTOR_DETAIL_KEY, id);
                startActivity(intent);

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
            Intent intent = new Intent(MovieDetailsActivity.this, HomeActivity.class);
            startActivity(intent);
        }
        else if (id == android.R.id.home){
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setMovie(String apiToken, int id){

        final String apiToken2 = apiToken;
        String fields = "movieId,director,imdb_id,homepage,imdb_rating,metascore,overview,popularity,poster_path,release_date,runtime,title,tomato_meter,tomato_user_meter,vote_average";

        ApiManagerMovie.getService().getMovie(apiToken, fields, id, new Callback<ApiWraper>() {
            @Override
            public void success(ApiWraper movie, Response response) {
                final MovieDetail m = movie.getMovie();
                try {
                    overviewTV.setText(m.getOverview());
                    Picasso.with(getApplicationContext()).load(m.getImage()).fit().placeholder(R.drawable.large_movie_poster).into(thumbnailIV);
                    collapsingToolbar.setTitle(m.getTitle());


                    String mDate = m.getRelease_date().split("T")[0].replace('-', '.');


                    releaseDateTV.setText(mDate);
                    runtimeTV.setText(m.getRuntime());
                    tvImdb.setText(m.getImdb_rating());
                    tvMetaCritic.setText(m.getMetascore());
                    getTvRottenTomatoUser.setText(m.getTomato_user_meter());
                    tvRottenTomato.setText(m.getTomato_meter());
                    directors.setText(m.getDirector());

                    imdbButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Uri imdbUri = Uri.parse(LoginActivity.IMDB_MOVIE_LINK + m.getImdb_id());
                            Intent imdb = new Intent(Intent.ACTION_VIEW, imdbUri);
                            startActivity(imdb);

                        }
                    });

                    homepageButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Uri imdbUri = Uri.parse(m.getHomepage());
                            Intent imdb = new Intent(Intent.ACTION_VIEW, imdbUri);
                            startActivity(imdb);

                        }
                    });

                    crewLink.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent crewIntent = new Intent(MovieDetailsActivity.this, CrewActivity.class);
                            crewIntent.putExtra("Id", m.getId());
                            startActivity(crewIntent);

                        }
                    });

                    swiper.findViewById(R.id.sharer).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            shareImage(m.getImdb_id(), m.getTitle(), m.getImage());
                        }
                    });

                    setCast(apiToken2, m.getId());

                } catch (Exception exc) {

                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(MovieDetailsActivity.this, "Something happened :(", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void shareImage(String uri, String title, final String imageUri){
        final String url = uri;
        final String imageUrl = imageUri;
        final String titles = title;
        AlertDialog.Builder shareDialog = new AlertDialog.Builder(this);
        shareDialog.setTitle("Share movie");
        shareDialog.setMessage("Share movie to Facebook?");
        shareDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //share the image to Facebook
                ShareLinkContent content = new ShareLinkContent.Builder()
                        .setContentUrl(Uri.parse(LoginActivity.IMDB_MOVIE_LINK + url))
                        .setImageUrl(Uri.parse(imageUri))
                        .setContentTitle(titles + " is AWESOME!!")
                        .setContentDescription("Great movie, you should definitely watch this piece of art!! Thx Filmster")
                        .build();
                shareButton.setShareContent(content);
                shareButton.performClick();
            }
        });
        shareDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        shareDialog.show();
    }

    private void setCast(String token, int id){
        ApiManagerMovie.getService().getCast(token, "movieId,cast", id, new Callback<CastWrapper>() {
            @Override
            public void success(CastWrapper wrapper, Response response) {

                CastList castList = wrapper.getCastList();

                List<ActorMinified> smallCast = castList.getSmallCast();

                if(smallCast.size() > 0){
                    ActorMinified a1 = smallCast.get(0);
                    firstTV.setText(a1.getName());
                    Picasso.with(getApplicationContext()).load(a1.getProfilePictureUrl()).placeholder(R.drawable.person_placeholder).into(firstIV);
                    RelativeLayout rl1 = (RelativeLayout) findViewById(R.id.firstLayout);
                    rl1.setOnClickListener(setActorDetailsListener(a1.getId()));
                }

                if(smallCast.size() > 1){
                    ActorMinified a2 = smallCast.get(1);
                    secondTV.setText(a2.getName());
                    Picasso.with(getApplicationContext()).load(a2.getProfilePictureUrl()).placeholder(R.drawable.person_placeholder).into(secondIV);
                    RelativeLayout rl2 = (RelativeLayout) findViewById(R.id.secondLayout);
                    rl2.setOnClickListener(setActorDetailsListener(a2.getId()));
                }

                if(smallCast.size() > 2){
                    ActorMinified a3 = smallCast.get(2);
                    thirdTV.setText(a3.getName());
                    Picasso.with(getApplicationContext()).load(a3.getProfilePictureUrl()).placeholder(R.drawable.person_placeholder).into(thirdIV);
                    RelativeLayout rl3 = (RelativeLayout) findViewById(R.id.thirdLayout);
                    rl3.setOnClickListener(setActorDetailsListener(a3.getId()));
                }

                if(smallCast.size() > 3){
                    ActorMinified a4 = smallCast.get(3);
                    fourthTV.setText(a4.getName());
                    Picasso.with(getApplicationContext()).load(a4.getProfilePictureUrl()).placeholder(R.drawable.person_placeholder).into(fourthIV);
                    RelativeLayout rl4 = (RelativeLayout) findViewById(R.id.fourthLayout);
                    rl4.setOnClickListener(setActorDetailsListener(a4.getId()));
                }

                if(smallCast.size() > 4){
                    ActorMinified a5 = smallCast.get(4);
                    fifthTV.setText(a5.getName());
                    Picasso.with(getApplicationContext()).load(a5.getProfilePictureUrl()).placeholder(R.drawable.person_placeholder).into(fifthIV);
                    RelativeLayout rl5 = (RelativeLayout) findViewById(R.id.fifthLayout);
                    rl5.setOnClickListener(setActorDetailsListener(a5.getId()));
                }

                if(smallCast.size() > 5){
                    ActorMinified a6 = smallCast.get(5);
                    sixthTV.setText(a6.getName());
                    Picasso.with(getApplicationContext()).load(a6.getProfilePictureUrl()).placeholder(R.drawable.person_placeholder).into(sixthIV);
                    RelativeLayout rl6 = (RelativeLayout) findViewById(R.id.sixthLayout);
                    rl6.setOnClickListener(setActorDetailsListener(a6.getId()));
                }

            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(MovieDetailsActivity.this, "Something happened :(", Toast.LENGTH_LONG).show();
            }
        });
    }

    private View.OnClickListener setActorDetailsListener(final int actorId){

        return new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieDetailsActivity.this, ActorDetailActivity.class);
                intent.putExtra(LoginActivity.ACTOR_DETAIL_KEY, actorId);
                startActivity(intent);
            }

        };

    }

}
