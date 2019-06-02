package in.itechvalley.topmoviesoffline.repo;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.UnknownHostException;
import java.util.List;

import in.itechvalley.topmoviesoffline.database.TopMoviesDatabase;
import in.itechvalley.topmoviesoffline.model.SingleMovieModel;
import in.itechvalley.topmoviesoffline.model.TopMoviesModel;
import in.itechvalley.topmoviesoffline.service.ApiController;
import in.itechvalley.topmoviesoffline.service.RetrofitService;
import in.itechvalley.topmoviesoffline.utils.NetworkHelper;
import in.itechvalley.topmoviesoffline.utils.constants.StatusCodes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopMoviesRepo
{
    /*
    * LOG
    * */
    private static final String TAG = "TopMoviesRepo";

    /*
    * Singleton Approach
    * */
    private static TopMoviesRepo INSTANCE;

    /*
    * Global Instance of Database
    * */
    private final TopMoviesDatabase topMoviesDatabase;

    /*
    * LiveData (Observe)
    * MutableLiveData (Observe + Post)
    * MediatorLiveData (Single Source of Truth - Database)
    * */
    private MediatorLiveData<List<SingleMovieModel>> databaseObserver;

    private MutableLiveData<Integer> networkStatusObserver = new MutableLiveData<>();

    /*
    * Method to return the Instance of this class
    * Singleton Approach
    * */
    public static TopMoviesRepo getInstance(TopMoviesDatabase topMoviesDatabase)
    {
        if (INSTANCE == null)
        {
            INSTANCE = new TopMoviesRepo(topMoviesDatabase);
        }

        return INSTANCE;
    }

    /*
    * Constructor
    * */
    public TopMoviesRepo(TopMoviesDatabase topMoviesDatabase)
    {
        this.topMoviesDatabase = topMoviesDatabase;

        LiveData<List<SingleMovieModel>> liveMovieList =
                topMoviesDatabase.getTopMovieDao().getAllMovies();

        /*
        * Observe the Databse forever [Database Connection]
        * */
        databaseObserver = new MediatorLiveData<>();
        databaseObserver.addSource(liveMovieList, new Observer<List<SingleMovieModel>>()
        {
            @Override
            public void onChanged(List<SingleMovieModel> movieListFromDatabase)
            {
                if (movieListFromDatabase != null)
                    databaseObserver.postValue(movieListFromDatabase);
            }
        });
    }

    public void getAllMoviesFromServer()
    {
        new RetrofitService()
                .getRetrofit()
                .create(ApiController.class)
                .getAllMovies()
                .enqueue(new Callback<TopMoviesModel>()
                {
                    @Override
                    public void onResponse(@NonNull Call<TopMoviesModel> call, @NonNull Response<TopMoviesModel> response)
                    {
                        TopMoviesModel responseBody = response.body();
                        if (responseBody == null)
                        {
                            networkStatusObserver.postValue(StatusCodes.ERROR_CODE_RESPONSE_BODY_NULL);
                            return;
                        }

                        if (responseBody.isSuccess())
                        {
                            networkStatusObserver.postValue(StatusCodes.STATUS_CODE_SUCCESS);

                            Thread thread = new Thread(() ->
                            {
                                List<SingleMovieModel> moviesList = responseBody.getMoviesList();

                                /*
                                * Truncate Table
                                * */
                                int noOfRowsDeleted = topMoviesDatabase.getTopMovieDao().truncateTable();
                                Log.i(TAG, String.format("Total %d movies deleted", noOfRowsDeleted));

                                /*
                                 * Connection Between Retrofit and Database
                                 * */
                                long[] insertionIds = topMoviesDatabase.getTopMovieDao()
                                        .cacheAllMovies(moviesList);
                                for (long insertionId : insertionIds)
                                    Log.i(TAG, String.format("Movie with %d inserted", insertionId));
                            });
                            thread.start();
                        }
                        else
                        {
                            networkStatusObserver.postValue(StatusCodes.ERROR_CODE_RESPONSE_FAILED);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<TopMoviesModel> call, @NonNull Throwable throwable)
                    {
                        networkStatusObserver.postValue(
                                NetworkHelper.getNetworkErrorCode(throwable)
                        );

                        Log.e(TAG, "Failed to Fetch Movies from Server", throwable);
                    }
                });
    }

    public MediatorLiveData<List<SingleMovieModel>> getDatabaseObserver()
    {
        return databaseObserver;
    }

    public MutableLiveData<Integer> getNetworkStatusObserver()
    {
        return networkStatusObserver;
    }
}
