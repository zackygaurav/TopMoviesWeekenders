package in.itechvalley.topmoviesoffline.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TopMoviesModel implements Serializable
{
    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("movies")
    private List<SingleMovieModel> moviesList;

    /*
    * Constructor
    * */
    public TopMoviesModel(boolean success, String message, List<SingleMovieModel> moviesList)
    {
        this.success = success;
        this.message = message;
        this.moviesList = moviesList;
    }

    /*
    * Getters
    * */
    public boolean isSuccess()
    {
        return success;
    }

    public String getMessage()
    {
        return message;
    }

    public List<SingleMovieModel> getMoviesList()
    {
        return moviesList;
    }
}
