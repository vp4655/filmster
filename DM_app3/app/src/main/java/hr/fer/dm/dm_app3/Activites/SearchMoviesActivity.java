package hr.fer.dm.dm_app3.Activites;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import hr.fer.dm.dm_app3.R;

public class SearchMoviesActivity extends AppCompatActivity {

    @Bind(R.id.searchMovies) SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movies);
        ButterKnife.bind(this);

        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Toast.makeText(SearchMoviesActivity.this, "Query: " + query, Toast.LENGTH_LONG).show();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        //changeSearchViewTextColor(searchView);
        ImageView closeBtn = (ImageView) findViewById(R.id.search_close_btn);

        searchView.requestFocus();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);


        //searchView.setBackgroundColor(getResources().getColor(R.color.gold));
//        View v = searchView.findViewById(android.support.v7.appcompat.R.id.search_plate);
//        v.setBackgroundColor(getResources().getColor(R.color.gold));

//        // Getting id for 'search_plate' - the id is part of generate R file,
//        // so we have to get id on runtime.
//        int searchPlateId = searchView.getContext().getResources().getIdentifier("android:id/search_plate", null, null);
//        // Getting the 'search_plate' LinearLayout.
//        View searchPlate = searchView.findViewById(searchPlateId);
//        // Setting background of 'search_plate' to earlier defined drawable.
//        searchPlate.setBackgroundResource(R.drawable.textfield_searchview_holo_light);

        int searchPlateId = searchView.getContext().getResources()
                .getIdentifier("android:id/search_plate", null, null);
        View searchPlateView = searchView.findViewById(searchPlateId);
        if (searchPlateView != null) {
            searchPlateView.setBackgroundColor(Color.WHITE); //depand you can set
        }
    }

    private void changeSearchViewTextColor(View view) {
        if (view != null) {
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(Color.WHITE);
                return;
            } else if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    changeSearchViewTextColor(viewGroup.getChildAt(i));
                }
            }
        }
    }
}
