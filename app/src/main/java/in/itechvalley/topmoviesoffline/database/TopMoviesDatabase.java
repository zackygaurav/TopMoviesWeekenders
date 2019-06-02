package in.itechvalley.topmoviesoffline.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import in.itechvalley.topmoviesoffline.model.SingleMovieModel;

@Database(entities = {SingleMovieModel.class}, version = 1)
public abstract class TopMoviesDatabase extends RoomDatabase
{
    /*
    * Database Name
    * */
    private static final String DATABASE_NAME = "topmovies.db";

    /*
    * Singleton Approach and SOC
    *
    * Instance of this class should be only created by
    * this class.
    * */
    private static TopMoviesDatabase INSTANCE;

    /*
    * A Method that returns the Instance of Dao associated with this
    * Database
    * */
    // Syntax abstract ReturningDao getReturningDao();
    public abstract TopMoviesDao getTopMovieDao();

    /*
    * A Method that returns the Instance of this class
    * */
    public static TopMoviesDatabase getInstance(@NonNull Context context)
    {
        if (INSTANCE == null)
        {
            /*
            * Init INSTANCE
            * */
            INSTANCE = Room.databaseBuilder(
                    context,
                    TopMoviesDatabase.class,
                    DATABASE_NAME
            ).build();
        }

        return INSTANCE;
    }
}
