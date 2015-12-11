package hr.fer.dm.dm_app3.ListViewItems;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.squareup.picasso.Picasso;

import hr.fer.dm.dm_app3.ListViewItems.Actor;
import hr.fer.dm.dm_app3.R;
import hr.fer.dm.dm_app3.Util.AppController;

import java.util.ArrayList;

/**
 * Created by Valentino on 11.12.2015..
 */
public class ActorsAdapter extends ArrayAdapter<Actor> {

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public ActorsAdapter(Context context, ArrayList<Actor> aActors){
        super(context, 0, aActors);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Actor actor = getItem(position);

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.actor, parent, false);
        }

        if(imageLoader == null){
            imageLoader = AppController.getInstance().getImageLoader();
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        ImageView ivProfileImage = (ImageView) convertView.findViewById(R.id.ivProfileImage);

        tvName.setText(actor.getName());
        Picasso.with(getContext()).load(actor.getProfilePictureUrl()).into(ivProfileImage);

        return null;
    }
}
