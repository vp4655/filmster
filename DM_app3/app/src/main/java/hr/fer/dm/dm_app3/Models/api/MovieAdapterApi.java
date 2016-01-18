package hr.fer.dm.dm_app3.Models.api;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import hr.fer.dm.dm_app3.R;
import hr.fer.dm.dm_app3.RecyclerViewHolders.RecyclerHeaderViewHolder;

/**
 * Created by Kajkara on 17.1.2016..
 */
public class MovieAdapterApi extends RecyclerView.Adapter<MovieItemViewHolderApi> implements View.OnClickListener {

    private static final int TYPE_HEADER = 2;
    private static final int TYPE_ITEM = 1;

    private List<MovieApi> movieItems;
    private View mView;

    public MovieAdapterApi(List<MovieApi> list) {
        movieItems = list;
    }

    @Override
    public MovieItemViewHolderApi onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
//        if (viewType == TYPE_ITEM) {
//            mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_listitem, parent, false);
//            return RecyclerItemViewHolder.newInstance(mView);
//        } else if (viewType == TYPE_HEADER) {
//            final View view = LayoutInflater.from(context).inflate(R.layout.recycler_header, parent, false);
//            return new RecyclerHeaderViewHolder(view);
//        }
//        throw new RuntimeException("There is no type that matches the type " + viewType + " + make sure your using types correctly");

        mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_listitem, parent, false);
        //return RecyclerItemViewHolder.newInstance(mView);
        return new MovieItemViewHolderApi(mView);
    }

    @Override
    public void onBindViewHolder(MovieItemViewHolderApi holder, int position) {
        holder.setContent(mView, movieItems.get(position));
        holder.itemView.setTag(movieItems.get(position));

    }

//    public long getItemId(int position) {
//        return movieItems.get(position).get;
//    }

    public Object getItem(int position){
        return movieItems.get(position);
    }

    //our old getItemCount()
    public int getBasicItemCount() {
        return movieItems == null ? 0 : movieItems.size();
    }

    //our new getItemCount() that includes header View
    @Override
    public int getItemCount() {
        return getBasicItemCount() ; // header
    }

    //added a method that returns viewType for a given position
    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    //added a method to check if given position is a header
    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    @Override
    public void onClick(View v) {

    }

    public void addMovies(List<MovieApi> movieItems) {
        this.movieItems.addAll(movieItems);
        //notifyItemInserted(getMovies().size()-1);
        notifyDataSetChanged();

    }

    public List<MovieApi> getMovies() {
        return this.movieItems;

    }

}
