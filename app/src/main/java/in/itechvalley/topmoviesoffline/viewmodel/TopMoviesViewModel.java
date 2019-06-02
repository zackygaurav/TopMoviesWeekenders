package in.itechvalley.topmoviesoffline.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import in.itechvalley.topmoviesoffline.TopMoviesApp;
import in.itechvalley.topmoviesoffline.model.SingleMovieModel;
import in.itechvalley.topmoviesoffline.repo.TopMoviesRepo;

public class TopMoviesViewModel extends AndroidViewModel
{
    /*
    * Global Instance of TopMoviesRepo
    * */
    private TopMoviesRepo repo;

    /*
    * Constructor
    * */
    public TopMoviesViewModel(@NonNull Application application)
    {
        super(application);

        /*
         * Injecting Instance of TopMoviesRepo via Application class
         * */
        repo = ((TopMoviesApp) application).provideTopMoviesRepo();
    }

    /*
    * This method will be called by Any Lifeycle Owner
    * */
    public void requestMovieList()
    {
        repo.getAllMoviesFromServer();
    }

    public LiveData<List<SingleMovieModel>> observeMovieList()
    {
        return repo.getDatabaseObserver();
    }

    public LiveData<Integer> observeApiStatus()
    {
        return repo.getNetworkStatusObserver();
    }
}
