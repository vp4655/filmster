package hr.fer.dm.dm_app3.Classes.Fab;

import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.List;

import hr.fer.dm.dm_app3.Classes.SearchOption;

/**
 * Created by Kajkara on 15.1.2016..
 */
public class FilterOptions {
    private String title;
    private List<String> genres;
    private int numFrom;
    private int numTo;
    private boolean isYearChecked;

    public FilterOptions()
    {
        title= "";
        genres = new ArrayList<String>();
        numFrom=0;
        numTo=0;
        isYearChecked = false;
    }

    public FilterOptions(String title_)
    {
        title= title_;
    }
    public boolean getIsYearChecked() {
        return isYearChecked;
    }

    public void setIsYearChecked(boolean b) {
        this.isYearChecked = b;
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


    public boolean filter()
    {
        if(genresSelected() || isYearChecked || title!="") return true;
        else return false;
    }

    public boolean genresSelected()
    {
        return (genres.size()!=0);
    }

    public SearchOption getOption()
    {
        if(title=="" && !genresSelected())
            return SearchOption.None;
        else if (title=="" && genresSelected())
            return SearchOption.Genre;
        else if (title!="" && !genresSelected())
            return SearchOption.Name;
        else if (title!="" && genresSelected())
            return SearchOption.NameGenre;
        else
            return SearchOption.None;
    }

}



//{ "name" : "Adventure", "id" : 12 }
//        { "name" : "Mystery", "id" : 9648 }
//        { "name" : "Science Fiction", "id" : 878 }
//        { "name" : "Thriller", "id" : 53 }
//        { "name" : "Drama", "id" : 18 }
//        { "name" : "Romance", "id" : 10749 }
//        { "name" : "Crime", "id" : 80 }
//        { "name" : "Comedy", "id" : 35 }
//        { "name" : "Music", "id" : 10402 }
//        { "name" : "Foreign", "id" : 10769 }
//        { "name" : "Documentary", "id" : 99 }
//        { "name" : "Animation", "id" : 16 }
//        { "name" : "Action", "id" : 28 }
//        { "name" : "Fantasy", "id" : 14 }
//        { "name" : "History", "id" : 36 }
//        { "name" : "War", "id" : 10752 }
//        { "name" : "Family", "id" : 10751 }
//        { "name" : "Horror", "id" : 27 }
//        { "name" : "Western", "id" : 37 }
//        { "name" : "TV Movie", "id" : 10770 }
//        { "name" : "Kids", "id" : 10762 }
//        { "name" : "Reality", "id" : 10764 }