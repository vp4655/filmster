package hr.fer.dm.dm_app3.Activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import hr.fer.dm.dm_app3.ListViewItems.ActorMinified;
import hr.fer.dm.dm_app3.ListViewItems.ActorsMinifiedAdapter;
import hr.fer.dm.dm_app3.R;

public class CastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast);

        //6 rucno dodanih

        ArrayList<ActorMinified> aActors = new ArrayList<ActorMinified>();

        ActorMinified a1 = new ActorMinified();
        a1.setName("Mark Hamill");
        a1.setCharacterName("Luke Skywalker");
        a1.setProfilePictureUrl("/zUXHs0t0rhRNg7rD1pQm09KXAKP.jpg");
        a1.setId(2);

        ActorMinified a2 = new ActorMinified();
        a2.setName("Harrison Ford");
        a2.setCharacterName("Han Solo");
        a2.setProfilePictureUrl("/7CcoVFTogQgex2kJkXKMe8qHZrC.jpg");
        a2.setId(3);

        ActorMinified a3 = new ActorMinified();
        a3.setName("Carrie Fisher");
        a3.setCharacterName("Princess Leia Organa");
        a3.setProfilePictureUrl("/oVYiGe4GzgQkoJfdHg8qKqEoWJz.jpg");
        a3.setId(4);

        ActorMinified a4 = new ActorMinified();
        a4.setName("Peter Cushing");
        a4.setCharacterName("Grand Moff Tarkin");
        a4.setProfilePictureUrl("/iFE9Xi5B0eZcNFqvCx78UUzmUfI.jpg");
        a4.setId(5);

        ActorMinified a5 = new ActorMinified();
        a5.setName("Alec Guinness");
        a5.setCharacterName("Ben (Obi-Wan) Kenobi");
        a5.setProfilePictureUrl("/nv3ppxgUQJytFGXZNde4f9ZlshB.jpg");
        a5.setId(12248);

        ActorMinified a6 = new ActorMinified();
        a6.setName("Anthony Daniels");
        a6.setCharacterName("See Threepio (C-3PO)");
        a6.setProfilePictureUrl("/cljvryjb3VwTsNR7fjQKjNPMaBB.jpg");
        a6.setId(612);

        aActors.add(a1);
        aActors.add(a2);
        aActors.add(a3);
        aActors.add(a4);
        aActors.add(a5);
        aActors.add(a6);

        ActorsMinifiedAdapter adapter = new ActorsMinifiedAdapter(this, aActors);
        ListView listView = (ListView) findViewById(R.id.lvCast);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                ActorMinified actorMin = (ActorMinified) parent.getAdapter().getItem(position);
                Intent intent = new Intent(CastActivity.this, ActorDetailActivity.class);
                intent.putExtra(LoginActivity.ACTOR_DETAIL_KEY, actorMin.getId());
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cast, menu);
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
