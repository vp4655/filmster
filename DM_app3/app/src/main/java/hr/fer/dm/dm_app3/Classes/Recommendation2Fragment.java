package hr.fer.dm.dm_app3.Classes;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import hr.fer.dm.dm_app3.Models.themoviedb.MovieAdapter_themovie;
import hr.fer.dm.dm_app3.R;
import hr.fer.dm.dm_app3.Models.themoviedb.Moviedx;
import hr.fer.dm.dm_app3.Network.ApiManager;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Kajkara on 9.12.2015..
 */
public class Recommendation2Fragment extends BaseFragment{
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    @Bind(R.id.lvMovies_Recomm2) ListView listView;
    private MovieAdapter_themovie adapter;


    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Recommendation2Fragment newInstance(int sectionNumber) {
        Recommendation2Fragment fragment = new Recommendation2Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home_recomm2, container, false);
        ButterKnife.bind(this, rootView);

        if(movieList.isEmpty())
        {
            getMovies();
        }
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position,
//                                    long id) {
//                Movie entry = (Movie) parent.getAdapter().getItem(position);
//                Intent intent = new Intent(getActivity(), MovieDetailsActivity.class);
//                intent.putExtra("Title", entry.getTitle());     // // TODO: 10.12.2015. title zamijeniti s ID
//                startActivity(intent);
////                String text = entry.getTitle() + " is pressed!:)";
////                Toast.makeText(getActivity(),text,Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//                if (scrollState == SCROLL_STATE_IDLE) {
//                    if (listView.getLastVisiblePosition() >= listView.getCount() - 1 - threshold) {
//                        currentPage++;
//                        //load more list items:
////                      loadElements(currentPage);
//                        //// TODO: 10.12.2015. dohvat podataka u listu ovisno o stranici na kojoj se nalazimo
//
//                    }
//                }
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                //nikad ni ne ude??
//                //                boolean loadMore = /* maybe add a padding */
//                //                        firstVisibleItem + visibleItemCount >= totalItemCount;
//                //
//                //        if(loadMore) {
//                ////            adapter.getCount() += visibleItemCount; // or any other amount
//                //            adapter.notifyDataSetChanged();
//                //        }
//            }
//        });

        return rootView;
    }


    @Override
    public void getMovies() {
        pDialog = new ProgressDialog(getActivity());
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        ApiManager.getService().getMovies(new Callback<Moviedx>() {
            @Override
            public void success(Moviedx m, Response response) {
                try
                {
                    hidePDialog();
                    adapter = new MovieAdapter_themovie(getActivity(), m.getMovieList());
                    listView.setAdapter(adapter);
                }
                catch (Exception exc)
                {

                }
            }
            @Override
            public void failure(RetrofitError error) {
                hidePDialog();
                Toast.makeText(getActivity(), "Something happened :(", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void getMoviesLazy(int page) {
        // TODO: Lazy load - ovdje ne treba jer ima samo top 20 ili mozemo vise dohvatiti?
    }
}

