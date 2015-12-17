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

        int ajDI = (int) getIntent().getIntExtra(LoginActivity.ACTOR_DETAIL_KEY, 9576);
        Actor actor = new Actor();
        actor.setId(2888);
        actor.setName("Will Smith" +  Integer.toString(ajDI));
        actor.setBiography("Willard Christopher Will Smith, Jr. (born September 25, 1968) is an American actor, film producer and pop rapper. He has enjoyed success in music, television and film. In April 2007, Newsweek called him the most powerful actor on the planet. Smith has been nominated for four Golden Globe Awards, two Academy Awards, and has won multiple Grammy Awards. In the late 1980s, Smith achieved modest fame as a rapper under the name The Fresh Prince. In 1990, his popularity increased dramatically when he starred in the popular television series The Fresh Prince of Bel-Air. The show ran for nearly six years (1990–1996) on NBC and has been syndicated consistently on various networks since then. In the mid-1990s, Smith transitioned from television to film, and ultimately starred in numerous blockbuster films that received broad box office success. In fact, he is the only actor in history to have eight consecutive films gross over $100 million in the domestic box office as well as being the only actor to have eight consecutive films in which he starred open at the #1 spot in the domestic box office tally. Fourteen of the 19 fiction films he has acted in have accumulated worldwide gross earnings of over $100 million, and 4 of them took in over $500 million in global box office receipts. His most financially successful films have been Bad Boys, Bad Boys II, Independence Day, Men in Black, Men in Black II, I, Robot, The Pursuit of Happyness, I Am Legend, Hancock, Wild Wild West, Enemy of the State, Shark Tale, Hitch, and Seven Pounds. He also earned critical praise for his performances in Six Degrees of Separation, Ali, and The Pursuit of Happyness, receiving Best Actor Oscar nominations for the latter two. From Wikipedia, the free encyclopedia.");
        actor.setHomepageUrl("http://www.willsmith.com/");
        actor.setProfilePictureUrl("/2iYXDlCvLyVO49louRyDDXagZ0G.jpg");
        actor.setPopularity(5.297515);
        actor.setBirthDate("1968-09-25");
        actor.setDeathDate("");
        actor.setPlaceOfBirth("Philadelphia, Pennsylvania, USA");

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

        ivProfilePicture.setOnClickListener(imageAsLinkListener(actor.getHomepageUrl(), actor.getName()));

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
