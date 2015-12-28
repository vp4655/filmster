package hr.fer.dm.dm_app3.Activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import hr.fer.dm.dm_app3.ListViewItems.ActorMinified;
import hr.fer.dm.dm_app3.R;

public class MovieDetailsActivity extends AppCompatActivity {
    @Bind(R.id.tvTitle_md) TextView title;
    @Bind(R.id.castText) TextView castTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);

        String title_S = getIntent().getExtras().getString("Title");
        getSupportActionBar().setTitle(title_S);
        //title = (TextView)findViewById(R.id.tvTitle_md);
//        title.setText(title_S);

        //TextView castTV = (TextView) findViewById(R.id.castText);
        castTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    startActivity(new Intent(MovieDetailsActivity.this, CastActivity.class));

            }
        });

        //6 rucno dodanih

        ActorMinified a1 = new ActorMinified();
        a1.setName("Mark Hamill");
        a1.setProfilePictureUrl("/zUXHs0t0rhRNg7rD1pQm09KXAKP.jpg");
        a1.setId(2);

        ActorMinified a2 = new ActorMinified();
        a2.setName("Harrison Ford");
        a2.setProfilePictureUrl("/7CcoVFTogQgex2kJkXKMe8qHZrC.jpg");
        a2.setId(3);

        ActorMinified a3 = new ActorMinified();
        a3.setName("Carrie Fisher");
        a3.setProfilePictureUrl("/oVYiGe4GzgQkoJfdHg8qKqEoWJz.jpg");
        a3.setId(4);

        ActorMinified a4 = new ActorMinified();
        a4.setName("Peter Cushing");
        a4.setProfilePictureUrl("/iFE9Xi5B0eZcNFqvCx78UUzmUfI.jpg");
        a4.setId(5);

        ActorMinified a5 = new ActorMinified();
        a5.setName("Alec Guinness");
        a5.setProfilePictureUrl("/nv3ppxgUQJytFGXZNde4f9ZlshB.jpg");
        a5.setId(12248);

        ActorMinified a6 = new ActorMinified();
        a6.setName("Anthony Daniels");
        a6.setProfilePictureUrl("/cljvryjb3VwTsNR7fjQKjNPMaBB.jpg");
        a6.setId(6);

        ImageView firstIV = (ImageView) findViewById(R.id.firstImage);
        TextView firstTV = (TextView) findViewById(R.id.firstName);

        ImageView secondIV = (ImageView) findViewById(R.id.secondImage);
        TextView secondTV = (TextView) findViewById(R.id.secondName);

        ImageView thirdIV = (ImageView) findViewById(R.id.thirdImage);
        TextView thirdTV = (TextView) findViewById(R.id.thirdName);

        ImageView fourthIV = (ImageView) findViewById(R.id.fourthImage);
        TextView fourthTV = (TextView) findViewById(R.id.fourthName);

        ImageView fifthIV = (ImageView) findViewById(R.id.fifthImage);
        TextView fifthTV = (TextView) findViewById(R.id.fifthName);

        ImageView sixthIV = (ImageView) findViewById(R.id.sixthImage);
        TextView sixthTV = (TextView) findViewById(R.id.sixthName);

        firstTV.setText(a1.getName());
        Picasso.with(this).load(a1.getProfilePictureUrl()).placeholder(R.drawable.person_placeholder).into(firstIV);

        secondTV.setText(a2.getName());
        Picasso.with(this).load(a2.getProfilePictureUrl()).placeholder(R.drawable.person_placeholder).into(secondIV);

        thirdTV.setText(a3.getName());
        Picasso.with(this).load(a3.getProfilePictureUrl()).placeholder(R.drawable.person_placeholder).into(thirdIV);

        fourthTV.setText(a4.getName());
        Picasso.with(this).load(a4.getProfilePictureUrl()).placeholder(R.drawable.person_placeholder).into(fourthIV);

        fifthTV.setText(a5.getName());
        Picasso.with(this).load(a5.getProfilePictureUrl()).placeholder(R.drawable.person_placeholder).into(fifthIV);

        sixthTV.setText(a6.getName());
        Picasso.with(this).load(a6.getProfilePictureUrl()).placeholder(R.drawable.person_placeholder).into(sixthIV);

        //on click open actor

        RelativeLayout rl1 = (RelativeLayout) findViewById(R.id.firstLayout);
        RelativeLayout rl2 = (RelativeLayout) findViewById(R.id.secondLayout);
        RelativeLayout rl3 = (RelativeLayout) findViewById(R.id.thirdLayout);
        RelativeLayout rl4 = (RelativeLayout) findViewById(R.id.fourthLayout);
        RelativeLayout rl5 = (RelativeLayout) findViewById(R.id.fifthLayout);
        RelativeLayout rl6 = (RelativeLayout) findViewById(R.id.sixthLayout);

        rl1.setOnClickListener(setActorDetailsListener(a1.getId()));

        rl2.setOnClickListener(setActorDetailsListener(a2.getId()));

        rl3.setOnClickListener(setActorDetailsListener(a3.getId()));

        rl4.setOnClickListener(setActorDetailsListener(a4.getId()));

        rl5.setOnClickListener(setActorDetailsListener(a5.getId()));

        rl6.setOnClickListener(setActorDetailsListener(a6.getId()));


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
