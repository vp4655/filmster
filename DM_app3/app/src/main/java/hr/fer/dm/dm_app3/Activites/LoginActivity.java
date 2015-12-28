package hr.fer.dm.dm_app3.Activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import hr.fer.dm.dm_app3.R;

public class LoginActivity extends AppCompatActivity {

    // odmah na početku definiramo bindanje
    @Bind(R.id.btnSkip) Button skipButton;
    @Bind(R.id.actor_btn) Button actorButton;

    public static final String IMAGE_URL = "http://image.tmdb.org/t/p/w500";
    public static final String ACTOR_DETAIL_KEY = "actor";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);                         // u onCreate dodati ovo kako bi se bindanje obavilo

        skipButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    }
                }
        );


        //actorButton = (Button) findViewById(R.id.actor_btn);
        // linija gore sad ne treba jer butterknife to lijepo odmah binda

        actorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ActorDetailActivity.class);
                intent.putExtra(ACTOR_DETAIL_KEY, 0);
                startActivity(intent);
            }
        });

    }
}
