package hr.fer.dm.dm_app3.Activites;

import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import hr.fer.dm.dm_app3.ImageTransformations.CircleTransformation;
import hr.fer.dm.dm_app3.Models.actor.ActorDetail;
import hr.fer.dm.dm_app3.Network.ApiActorManager;
import hr.fer.dm.dm_app3.R;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ActorDetailActivity extends AppCompatActivity {
    private ImageView ivProfilePicture;
    private TextView name;
    private TextView date;
    private TextView placeOfBirth;
    private TextView popularity;
    private TextView biography;
    private TextView homepage;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor_detail);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        collapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));

        //TODO otkirti kako promjeniti boju menu-a i back button-a

        ivProfilePicture = (ImageView) findViewById(R.id.ivProfileImage);
        name = (TextView) findViewById(R.id.tvName);
        date = (TextView) findViewById(R.id.tvDate);
        placeOfBirth = (TextView) findViewById(R.id.tvPlaceOfBirth);
        popularity = (TextView) findViewById(R.id.tvPopularity);
        biography = (TextView) findViewById(R.id.tvBiography);
        homepage = (TextView) findViewById(R.id.tvHomepage);

        int ajDI = (int) getIntent().getIntExtra(LoginActivity.ACTOR_DETAIL_KEY, 9576);

        loadActor(ajDI);
    }

    public void loadActor(int id){

        //setTitle(actor.getName());
        collapsingToolbar.setTitle("");

        ApiActorManager.getService().getActor(id, new Callback<ActorDetail>() {
            @Override
            public void success(ActorDetail actorDetail, Response response) {

                final ActorDetail actor = actorDetail;

                try{

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
                    date.setText(Html.fromHtml("<b>Age </b>" + actor.getBirthday() + " - " + actor.getDeathday()));
                    placeOfBirth.setText(Html.fromHtml("<b>Place of birth :</b> " + actor.getPlace_of_birth()));
                    popularity.setText(Html.fromHtml("<b>Popularity : " + Double.toString(actor.getPopularity())));
                    biography.setText(Html.fromHtml("<b>Biography</b> : " + actor.getBiography()));
                    homepage.setText(Html.fromHtml("<b>Homepage : </b>" + actor.getHomepage()));
                    homepage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Uri uri = Uri.parse(actor.getHomepage());
                            Intent web = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(web);
                        }
                    });

                    Picasso.with(getApplicationContext()).load(actor.getImage()).transform(new CircleTransformation()).placeholder(R.drawable.person_placeholder).into(ivProfilePicture);

                    ivProfilePicture.setOnClickListener(imageAsLinkListener(actor.getHomepage(), actor.getName()));

                }
                catch (Exception e){

                }

            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(ActorDetailActivity.this, "Something happened :(", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actor_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
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
