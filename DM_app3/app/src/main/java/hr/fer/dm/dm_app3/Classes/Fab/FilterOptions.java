package hr.fer.dm.dm_app3.Classes.Fab;

import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kajkara on 15.1.2016..
 */
public class FilterOptions {
    private String title;
    private List<String> genres;
    private int numFrom;
    private int numTo;

    public FilterOptions()
    {
        title= "";
        genres = new ArrayList<String>();
        numFrom=0;
        numTo=0;
    }

    public FilterOptions(String title_)
    {
        title= title_;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public List<String> getGenres() {
        return genres;
    }

    public void setGenres (List<String> _genres)
    {
        genres = _genres;
    }

    public int getNumFrom() {
        return numFrom;
    }

    public void setNumFrom(int num) {
        this.numFrom= num;
    }

    public int getNumTo() {
        return numTo;
    }

    public void setNumTo(int num) {
        this.numTo= num;
    }


}
