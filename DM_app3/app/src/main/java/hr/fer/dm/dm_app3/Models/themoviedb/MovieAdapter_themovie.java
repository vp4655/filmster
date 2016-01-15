package hr.fer.dm.dm_app3.Models.themoviedb;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import hr.fer.dm.dm_app3.R;
import hr.fer.dm.dm_app3.Util.AppController;

/**
 * Created by Kajkara on 27.12.2015..
 */

// TODO: koristimo li????
public class MovieAdapter_themovie extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Movie> movieItems;

    //TODO: butterknife from adapter??
//    @Bind(R.id.title) TextView title;
//    @Bind(R.id.rating) TextView rating;
//    @Bind(R.id.genre) TextView genre;
//    @Bind(R.id.releaseYear) TextView year;


    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public MovieAdapter_themovie(Activity activity, List<Movie> movieItems) {
        this.activity = activity;
        this.movieItems = movieItems;
        ButterKnife.bind(activity);
    }

    public void MovieAdapter_addMovies(List<Movie> movieItems) {
        for (Movie movie : movieItems) {
            this.movieItems.add(movie);
        }
    }

    @Override
    public int getCount() {
        return movieItems.size();
    }

    @Override
    public Object getItem(int location) {
        return movieItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.movie_listitem, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);

        // getting movie data for the row
        Movie m = movieItems.get(position);


        TextView title = (TextView) convertView.findViewById(R.id.title);
        //TextView rating = (TextView) convertView.findViewById(R.id.rating);
        TextView genre = (TextView) convertView.findViewById(R.id.genre);
        TextView year = (TextView) convertView.findViewById(R.id.releaseYear);

        // thumbnail image
        thumbNail.setImageUrl(m.getImage(), imageLoader);

        // title
        title.setText(m.getTitle());

        // rating
        //rating.setText("Rating: " + String.valueOf(m.getPopularity()));

        // genre
        String genreStr = "";
        int count = 0;
        for (String str : m.getGenres()) {
            genreStr += str + ", ";
        }

        genreStr = genreStr.length() > 0 ? genreStr.substring(0,genreStr.length() - 2) : genreStr;
        //TODO: genre preko datuma piše
//        if(count >4)
//            genreStr += "\n";
        genre.setText(genreStr);

        // release year
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat df_new = new SimpleDateFormat("dd.MM.yyyy.");
        Date startDate;
        try {
            startDate = df.parse(m.getRelease_date());
            String newDateString = df_new.format(startDate);
            year.setText(newDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertView;
    }
}

