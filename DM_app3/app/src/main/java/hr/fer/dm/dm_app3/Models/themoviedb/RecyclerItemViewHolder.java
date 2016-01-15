package hr.fer.dm.dm_app3.Models.themoviedb;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import hr.fer.dm.dm_app3.Activites.MovieDetailsActivity;
import hr.fer.dm.dm_app3.R;
import hr.fer.dm.dm_app3.Util.AppController;


/**
 * Created by Kajkara on 5.1.2016..
 */
public class RecyclerItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView title;
    private TextView rating;
    private TextView genre;
    private TextView year;
    private NetworkImageView thumbNail;
    private ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public RecyclerItemViewHolder(final View parent) {
        super(parent);
        title = (TextView) parent.findViewById(R.id.title);
        rating = (TextView) parent.findViewById(R.id.rating);
        genre = (TextView) parent.findViewById(R.id.genre);
        year = (TextView) parent.findViewById(R.id.releaseYear);
        thumbNail = (NetworkImageView) parent.findViewById(R.id.thumbnail);
        parent.setOnClickListener(this);
    }

    public static RecyclerItemViewHolder newInstance(View parent) {
//        title = (TextView) parent.findViewById(R.id.title);
//        rating = (TextView) parent.findViewById(R.id.rating);
//        genre = (TextView) parent.findViewById(R.id.genre);
//        year = (TextView) parent.findViewById(R.id.releaseYear);
//        thumbNail = (NetworkImageView) parent.findViewById(R.id.thumbnail);
        return new RecyclerItemViewHolder(parent);
    }

    public void setContent(View parent, Movie m) {

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        // thumbnail image
        thumbNail.setImageUrl(m.getImage(), imageLoader);

        // title
        title.setText(m.getTitle());

        // rating
        rating.setText("Rating: " + String.valueOf(m.getPopularity()));

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
    }

    @Override
    public void onClick(View v) {
        int position = getAdapterPosition();
        Movie m = (Movie)v.getTag();

        Intent intent = new Intent(v.getContext(), MovieDetailsActivity.class);
        intent.putExtra("Id", m.getId());

        v.getContext().startActivity(intent);

    }
}