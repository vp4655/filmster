package hr.fer.dm.dm_app3.Models.api;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import hr.fer.dm.dm_app3.R;

/**
 * Created by Kajkara on 18.1.2016..
 */
public class SearchMovieViewHolder extends RecyclerView.ViewHolder {

    private final TextView mTitleView;
    private final TextView mCharcaterTextView;
    private final ImageView mThumbnailImageView;

    public SearchMovieViewHolder(final View parent, TextView titleView, TextView charcaterView, ImageView thmbnailView) {
        super(parent);
        mTitleView = titleView;
        mCharcaterTextView = charcaterView;
        mThumbnailImageView = thmbnailView;
    }

    public static SearchMovieViewHolder newInstance(View parent) {
        TextView titleView = (TextView) parent.findViewById(R.id.nameActor);
        TextView characterView = (TextView) parent.findViewById(R.id.nameCharacter);
        ImageView thumbnailView = (ImageView) parent.findViewById(R.id.thumbnailActor);
        return new SearchMovieViewHolder(parent, titleView, characterView, thumbnailView);
    }

    public void setContent(View parent, String name, String character, String url) {
        mTitleView.setText(character);
        mCharcaterTextView.setText(name);
        Picasso.with(parent.getContext()).load(url).placeholder(R.drawable.person_placeholder).into(mThumbnailImageView);
    }

}
