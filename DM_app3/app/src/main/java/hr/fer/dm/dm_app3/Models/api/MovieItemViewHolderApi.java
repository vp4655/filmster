package hr.fer.dm.dm_app3.Models.api;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import hr.fer.dm.dm_app3.Activites.MovieDetailsActivity;
import hr.fer.dm.dm_app3.Models.genres.Genre;
import hr.fer.dm.dm_app3.Models.themoviedb.Movie;
import hr.fer.dm.dm_app3.R;
import hr.fer.dm.dm_app3.Util.AppController;

/**
 * Created by Kajkara on 17.1.2016..
 */
public class MovieItemViewHolderApi extends RecyclerView.ViewHolder implements View.OnClickListener {

    private static final int NUM_GENRES_SHOWED = 4;

    private TextView title;
    private TextView ratingImdb;
    private TextView ratingMeta;
    private TextView ratingRTomato;
    private TextView genre;
    private TextView year;
    private ImageView thumbNail;
    private ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public MovieItemViewHolderApi(final View parent) {
        super(parent);
        title = (TextView) parent.findViewById(R.id.title);
        ratingImdb = (TextView) parent.findViewById(R.id.tvImdb);
        ratingMeta = (TextView) parent.findViewById(R.id.tvMetaCritic);
        ratingRTomato = (TextView) parent.findViewById(R.id.tvRottenTomato);
        genre = (TextView) parent.findViewById(R.id.genre);
        year = (TextView) parent.findViewById(R.id.releaseYear);
        thumbNail = (ImageView) parent.findViewById(R.id.thumbnail);
        parent.setOnClickListener(this);
    }

    public static MovieItemViewHolderApi newInstance(View parent) {
//        title = (TextView) parent.findViewById(R.id.title);
//        rating = (TextView) parent.findViewById(R.id.rating);
//        genre = (TextView) parent.findViewById(R.id.genre);
//        year = (TextView) parent.findViewById(R.id.releaseYear);
//        thumbNail = (NetworkImageView) parent.findViewById(R.id.thumbnail);
        return new MovieItemViewHolderApi(parent);
    }

    public void setContent(View parent, MovieApi m) {

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        // thumbnail image
        Picasso.with(parent.getContext()).load(m.imdb_poster_link).placeholder(R.drawable.small_movie_poster).into(thumbNail);

        // title
        title.setText(m.getTitle());

        // rating
        ratingImdb.setText(m.getimdb_rating());
        ratingRTomato.setText(m.gettomato_rating());
        ratingMeta.setText(m.getmetascore());

        // genre
        String genreStr = "";
        int count = 0;
//
//        for (String str : m.getGenres()) {
//            genreStr += str + ", ";
//        }
//
//        genreStr = genreStr.length() > 0 ? genreStr.substring(0, genreStr.length() - 2) : genreStr;
//        //TODO: genre preko datuma piše
//        if(count >4)
//            genreStr += "\n";
//        genre.setText(genreStr);

        String comma = ", ";
        for (Genre g : m.getgenres()) {
            if (count==NUM_GENRES_SHOWED-1)
            {
                comma="...";
            }
            else if (count==m.getgenres().size()-1)
            {
                comma="";
            }
            genreStr += g.getName() + comma;

            count++;
            if (count==NUM_GENRES_SHOWED)
                break;
        }
        genre.setText(genreStr);

        // release year
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat df_new = new SimpleDateFormat("dd.MM.yyyy.");
        Date startDate;

        try {
            if(m.getrelease_date()!=null)
            {
                startDate = df.parse(m.getrelease_date());
                String newDateString = df_new.format(startDate);
                year.setText(newDateString);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        int position = getAdapterPosition();
        MovieApi m = (MovieApi)v.getTag();

        Intent intent = new Intent(v.getContext(), MovieDetailsActivity.class);
        intent.putExtra("Id", m.getmovieId());

        v.getContext().startActivity(intent);

    }
}

