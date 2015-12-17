package hr.fer.dm.dm_app3.ListViewItems;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import hr.fer.dm.dm_app3.R;
import hr.fer.dm.dm_app3.Util.AppController;

/**
 * Created by Valentino on 17.12.2015..
 */
public class ActorsMinifiedAdapter extends ArrayAdapter<ActorMinified>{

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public ActorsMinifiedAdapter(Context context, ArrayList<ActorMinified> aActors){
        super(context, 0, aActors);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ActorMinified actorMin = getItem(position);

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.two_way_list_item, parent, false);
        }

        if(imageLoader == null){
            imageLoader = AppController.getInstance().getImageLoader();
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.nameActor);
        TextView tvCharacter = (TextView) convertView.findViewById(R.id.nameCharacter);
        ImageView ivProfileImage = (ImageView) convertView.findViewById(R.id.thumbnailActor);

        tvName.setText(actorMin.getName());
        tvCharacter.setText(actorMin.getCharacterName());
        Picasso.with(getContext()).load(actorMin.getProfilePictureUrl()).into(ivProfileImage);

        return convertView;
    }

}
