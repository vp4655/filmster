package hr.fer.dm.dm_app3;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import hr.fer.dm.dm_app3.ListViewItems.ActorsAdapter;

/**
 * Created by Valentino on 11.12.2015..
 */
public class ActorActivity extends Activity {

    private ListView lvActors;
    private ActorsAdapter actorsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actor);
    }
}
