package hr.fer.dm.dm_app3;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Typeface;

import com.squareup.picasso.Picasso;

import hr.fer.dm.dm_app3.ImageTransformations.CircleTransformation;
import hr.fer.dm.dm_app3.ListViewItems.Actor;

public class ActorDetailActivity extends AppCompatActivity {
    private ImageView ivProfilePicture;
    private TextView name;
    private TextView date;
    private TextView placeOfBirth;
    private TextView popularity;
    private TextView biography;
    private TextView homepage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor_detail);

        ivProfilePicture = (ImageView) findViewById(R.id.ivProfileImage);
        name = (TextView) findViewById(R.id.tvName);
        date = (TextView) findViewById(R.id.tvDate);
        placeOfBirth = (TextView) findViewById(R.id.tvPlaceOfBirth);
        popularity = (TextView) findViewById(R.id.tvPopularity);
        biography = (TextView) findViewById(R.id.tvBiography);
        homepage = (TextView) findViewById(R.id.tvHomepage);

        Actor actor = (Actor) getIntent().getSerializableExtra(LoginActivity.ACTOR_DETAIL_KEY);

        loadActor(actor);
    }

    public void loadActor(final Actor actor){

        setTitle(actor.getName());

        name.setText(actor.getName());
        date.setText(Html.fromHtml("<b>Age </b>" + actor.getBirthDate() + " - " + actor.getDeathDate()));
        placeOfBirth.setText(Html.fromHtml("<b>Place of birth :</b> " + actor.getPlaceOfBirth()));
        popularity.setText(Html.fromHtml("<b>Popularity : " + Double.toString(actor.getPopularity())));
        biography.setText(Html.fromHtml("<b>Biography</b> : " + actor.getBiography()));
        homepage.setText(Html.fromHtml("<b>Homepage : </b>" + actor.getHomepageUrl()));
        homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(actor.getHomepageUrl());
                Intent web = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(web);
            }
        });

        Picasso.with(this).load(actor.getProfilePictureUrl()).transform(new CircleTransformation()).placeholder(R.drawable.person_placeholder).into(ivProfilePicture);
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

        return super.onOptionsItemSelected(item);
    }
}
