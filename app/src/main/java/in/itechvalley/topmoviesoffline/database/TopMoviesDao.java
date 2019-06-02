package in.itechvalley.topmoviesoffline.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import in.itechvalley.topmoviesoffline.model.SingleMovieModel;

/*
* An Interface which has all the CRUD Operation Queries
* */

@Dao
public interface TopMoviesDao
{
    /**
     * This is a helper method to Cache all the movies
     *
     * @param movieList List of movies to cache
     * @return Insertion ID's in Array format
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] cacheAllMovies(List<SingleMovieModel> movieList);

    @Insert
    long addSingleMovie(SingleMovieModel singleMovie);

    /**
     * This method returns the List of Movies in a
     * LiveData fashion
     *
     * @return LiveData of MoviesList
     */
    @Query("SELECT * FROM table_movies")
    LiveData<List<SingleMovieModel>> getAllMovies();

    @Query("SELECT * FROM table_movies WHERE title = :movieTitle LIMIT 1")
    SingleMovieModel getMovieByTitle(String movieTitle);

    @Query("SELECT * FROM table_movies WHERE title LIKE :title AND year LIKE :year")
    List<SingleMovieModel> searchMovies(String title, String year);

    @Query("DELETE FROM table_movies")
    int truncateTable();

    /*
    * What does room Return?
    *
    * Delete - int => No of rows deleted
    *
    * Insert (1 Object) - long => Insertion Row ID
    * Insert (n Object) - long[] => Insertion Row IDs
    *
    * Read
    * Single Object
    * Mutliple Object - [], List
    * */
}
