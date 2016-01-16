package hr.fer.dm.dm_app3.Activites;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
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
 * Created by Kajkara on 16.1.2016..
 */
public class DialogAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<String> genres;
    private CheckedTextView checkedTextView;
    public DialogAdapter(Activity activity, List<String> _genres) {
        this.activity = activity;
        this.genres = _genres;
    }

    @Override
    public int getCount() {
        return genres.size();
    }

    public int getNumItemToShow() {
        return 4;
    }

    @Override
    public Object getItem(int location) {
        return genres.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.checkgenres_item, null);

        checkedTextView = (CheckedTextView) convertView.findViewById(R.id.ctvGenre);
        checkedTextView.setText(genres.get(position));

        return convertView;
    }

//    Iterator<String> it = bAdapter.getCheckedItems().values().iterator();
//    for (int i=0;i<bAdapter.getCheckedItems().size();i++){
//        //Do whatever
//        bAdapter.getItem(Integer.parseInt(it.next());
//    }
//    public List<String> getCheckedGenres()
//    {
//        //if()
//    }

}