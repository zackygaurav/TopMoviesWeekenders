package in.itechvalley.topmoviesoffline;

import android.app.Application;

import in.itechvalley.topmoviesoffline.database.TopMoviesDatabase;
import in.itechvalley.topmoviesoffline.repo.TopMoviesRepo;

public class TopMoviesApp extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    public TopMoviesRepo provideTopMoviesRepo()
    {
        return TopMoviesRepo.getInstance(provideTopMoviesDatabase());
    }

    private TopMoviesDatabase provideTopMoviesDatabase()
    {
        return TopMoviesDatabase.getInstance(getBaseContext());
    }
}
