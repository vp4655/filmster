package hr.fer.dm.dm_app3.ListViewItems;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import hr.fer.dm.dm_app3.Models.actor.ActorMinified;
import hr.fer.dm.dm_app3.R;
import hr.fer.dm.dm_app3.RecyclerViewHolders.ActorMinifiedViewHolder;
import hr.fer.dm.dm_app3.RecyclerViewHolders.RecyclerHeaderViewHolder;

public class CastAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 2;
    private static final int TYPE_ITEM = 1;

    private List<ActorMinified> mActorList;
    private View mView;

    public CastAdapter(List<ActorMinified> actorList) {
        mActorList = actorList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        if (viewType == TYPE_ITEM) {
            mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.actor, parent, false);
            return ActorMinifiedViewHolder.newInstance(mView);
        } else if (viewType == TYPE_HEADER) {
            final View view = LayoutInflater.from(context).inflate(R.layout.recycler_header, parent, false);
            return new RecyclerHeaderViewHolder(view);
        }
        throw new RuntimeException("There is no type that matches the type " + viewType + " + make sure your using types    correctly");

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (!isPositionHeader(position)) {
            ActorMinifiedViewHolder holder = (ActorMinifiedViewHolder) viewHolder;
            ActorMinified actorMinified = mActorList.get(position - 1);
            holder.setContent(mView, actorMinified.getName(), actorMinified.getCharacterName(), actorMinified.getProfilePictureUrl());
        }
    }

    public Object getItem(int position){
        return mActorList.get(position);
    }

    //our old getItemCount()
    public int getBasicItemCount() {
        return mActorList == null ? 0 : mActorList.size();
    }

    //our new getItemCount() that includes header View
    @Override
    public int getItemCount() {
        return getBasicItemCount() + 1; // header
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

}
