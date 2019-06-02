package in.itechvalley.topmoviesoffline.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "table_movies", indices = @Index(value = "title", unique = true))
public class SingleMovieModel implements Serializable
{
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("ID")
    private int id;

    @ColumnInfo(name = "title")
    @SerializedName("Title")
    private String movieTitle;

    @ColumnInfo(name = "year")
    @SerializedName("Year")
    private String year;

    @ColumnInfo(name = "poster")
    @SerializedName(value = "Poster", alternate = "Poster ")
    private String poster;

    /*
    * Constructor
    * */
    public SingleMovieModel(int id, String movieTitle, String year, String poster)
    {
        this.id = id;
        this.movieTitle = movieTitle;
        this.year = year;
        this.poster = poster;
    }

    /*
    * Getters
    * */
    public int getId()
    {
        return id;
    }

    public String getMovieTitle()
    {
        return movieTitle;
    }

    public String getYear()
    {
        return year;
    }

    public String getPoster()
    {
        return poster;
    }
}
