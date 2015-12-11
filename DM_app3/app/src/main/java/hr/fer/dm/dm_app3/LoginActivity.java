package hr.fer.dm.dm_app3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import hr.fer.dm.dm_app3.ListViewItems.Actor;
import hr.fer.dm.dm_app3.ListViewItems.ActorsAdapter;

public class LoginActivity extends AppCompatActivity {
    private Button skipButton;
    private Button actorButton;
    private ActorsAdapter actorsAdapter;
    public static final String IMAGE_URL = "http://image.tmdb.org/t/p/w500";
    public static final String ACTOR_DETAIL_KEY = "actor";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        skipButton = (Button) findViewById(R.id.btnSkip);
        skipButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    }
                }
        );

        ArrayList<Actor> actors = new ArrayList<Actor>();
        actorsAdapter = new ActorsAdapter(this, actors);
        Actor actor = new Actor();
        actor.setId(2888);
        actor.setName("Will Smith");
        actor.setBiography("Willard Christopher Will Smith, Jr. (born September 25, 1968) is an American actor, film producer and pop rapper. He has enjoyed success in music, television and film. In April 2007, Newsweek called him the most powerful actor on the planet. Smith has been nominated for four Golden Globe Awards, two Academy Awards, and has won multiple Grammy Awards. In the late 1980s, Smith achieved modest fame as a rapper under the name The Fresh Prince. In 1990, his popularity increased dramatically when he starred in the popular television series The Fresh Prince of Bel-Air. The show ran for nearly six years (1990–1996) on NBC and has been syndicated consistently on various networks since then. In the mid-1990s, Smith transitioned from television to film, and ultimately starred in numerous blockbuster films that received broad box office success. In fact, he is the only actor in history to have eight consecutive films gross over $100 million in the domestic box office as well as being the only actor to have eight consecutive films in which he starred open at the #1 spot in the domestic box office tally. Fourteen of the 19 fiction films he has acted in have accumulated worldwide gross earnings of over $100 million, and 4 of them took in over $500 million in global box office receipts. His most financially successful films have been Bad Boys, Bad Boys II, Independence Day, Men in Black, Men in Black II, I, Robot, The Pursuit of Happyness, I Am Legend, Hancock, Wild Wild West, Enemy of the State, Shark Tale, Hitch, and Seven Pounds. He also earned critical praise for his performances in Six Degrees of Separation, Ali, and The Pursuit of Happyness, receiving Best Actor Oscar nominations for the latter two. From Wikipedia, the free encyclopedia.");
        actor.setHomepageUrl("http://www.willsmith.com/");
        actor.setProfilePictureUrl("/2iYXDlCvLyVO49louRyDDXagZ0G.jpg");
        actor.setPopularity(5.297515);
        actor.setBirthDate("1968-09-25");
        actor.setDeathDate("");
        actor.setPlaceOfBirth("Philadelphia, Pennsylvania, USA");
        actorsAdapter.add(actor);

        actorButton = (Button) findViewById(R.id.actor_btn);
        actorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ActorDetailActivity.class);
                intent.putExtra(ACTOR_DETAIL_KEY, actorsAdapter.getItem(0));
                startActivity(intent);
            }
        });

    }
}
