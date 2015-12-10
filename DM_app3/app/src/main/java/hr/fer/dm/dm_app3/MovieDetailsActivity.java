package hr.fer.dm.dm_app3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.widget.TextView;

public class MovieDetailsActivity extends AppCompatActivity {
    public TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        String title_S = getIntent().getExtras().getString("Title");
        title = (TextView)findViewById(R.id.tvTitle_md);
        title.setText(title_S);


    }
}
