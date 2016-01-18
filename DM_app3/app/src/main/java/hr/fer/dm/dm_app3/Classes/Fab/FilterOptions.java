package hr.fer.dm.dm_app3.Classes.Fab;

import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.List;

import hr.fer.dm.dm_app3.Classes.SearchOption;
import hr.fer.dm.dm_app3.Models.genres.Genre;

/**
 * Created by Kajkara on 15.1.2016..
 */
public class FilterOptions {
    private String title;
    private List<String> genres;
    private int numFrom;
    private int numTo;
    private boolean isYearChecked;

    private List<GenreSearch> gernesList;

    public FilterOptions()
    {
        title= "";
        genres = new ArrayList<String>();
        numFrom=0;
        numTo=0;
        isYearChecked = false;
        initGenres();
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
        boolean b = genresSelected();
        if(title.isEmpty() && !genresSelected())
            return SearchOption.None;
        else if (title.isEmpty() && genresSelected())
            return SearchOption.Genre;
        else if (!title.isEmpty() && !genresSelected())
            return SearchOption.Name;
        else if (!title.isEmpty()&& genresSelected())
            return SearchOption.NameGenre;
        else
            return SearchOption.None;
    }

    public void initGenres()
    {
        gernesList = new ArrayList<GenreSearch>();
        gernesList.add(new GenreSearch("Adventure", "12" ));
        gernesList.add(new GenreSearch("Mystery", "9648" ));
        gernesList.add(new GenreSearch("Science Fiction", "878" ));
        gernesList.add(new GenreSearch("Thriller", "53" ));
        gernesList.add(new GenreSearch("Drama", "18" ));
        gernesList.add(new GenreSearch("Romance", "10749" ));
        gernesList.add(new GenreSearch("Crime", "80" ));
        gernesList.add(new GenreSearch("Comedy", "35" ));
        gernesList.add(new GenreSearch("Music", "10402" ));
        gernesList.add(new GenreSearch("Foreign", "10769" ));
        gernesList.add(new GenreSearch("Documentary", "99" ));
        gernesList.add(new GenreSearch("Animation", "16" ));
        gernesList.add(new GenreSearch("Action", "28" ));
        gernesList.add(new GenreSearch("Fantasy", "14" ));
        gernesList.add(new GenreSearch("History", "35" ));
        gernesList.add(new GenreSearch("War", "10752" ));
        gernesList.add(new GenreSearch("Family", "10751" ));
        gernesList.add(new GenreSearch("Western", "37" ));
        gernesList.add(new GenreSearch("Horror", "27" ));
        gernesList.add(new GenreSearch("TV Movie", "10770" ));
        gernesList.add(new GenreSearch("Kids", "10762" ));
        gernesList.add(new GenreSearch("Reality", "10764"));

    }

    public List<String> stringList()
    {
        List<String> temp=new ArrayList<String>();
        for (GenreSearch g :gernesList) {
            temp.add(g.name);
        }
        return temp;
    }

    public String getGenreId()
    {
        List<Integer> temp=new ArrayList<Integer>();
        String s ="";
        int i =0;
        for (GenreSearch g :gernesList) {
            if(genres.contains(g.name))
            {
                s+=(g.id);
                i++;
                if(i<=genres.size()-1)
                    s+=(",");
            }
        }
        return s.toString();
    }

}


class GenreSearch
{
    public String name;
    public String id;

    public GenreSearch(String n, String i)
    {
        name=n;
        id=i;
    }
}


