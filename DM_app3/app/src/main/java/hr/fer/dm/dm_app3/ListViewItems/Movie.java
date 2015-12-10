package hr.fer.dm.dm_app3.ListViewItems;

/**
 * Created by Kajkara on 9.12.2015..
 */
import java.util.ArrayList;

public class Movie {
    private String title, thumbnailUrl;
    private int year;
    private double rating;
    private ArrayList<String> genre;

    public Movie() {
    }

    public Movie(String name, String thumbnailUrl, int year, double rating,
                 ArrayList<String> genre) {
        this.title = name;
        this.thumbnailUrl = thumbnailUrl;
        this.year = year;
        this.rating = rating;
        this.genre = genre;
    }

    //TITLE
    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    //THUMBNAIL_URL
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    //YEAR
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    //RATING
    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    //GENRE lista
    public ArrayList<String> getGenre() {
        return genre;
    }

    public void setGenre(ArrayList<String> genre) {
        this.genre = genre;
    }

}
