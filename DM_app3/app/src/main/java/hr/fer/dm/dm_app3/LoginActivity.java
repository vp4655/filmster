package hr.fer.dm.dm_app3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {
    private Button skipButton;
    private Button actorButton;
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


        actorButton = (Button) findViewById(R.id.actor_btn);
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
