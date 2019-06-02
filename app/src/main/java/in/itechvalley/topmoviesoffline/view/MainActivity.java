package in.itechvalley.topmoviesoffline.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.itechvalley.topmoviesoffline.R;
import in.itechvalley.topmoviesoffline.adapters.TopMoviesRecyclerAdapter;
import in.itechvalley.topmoviesoffline.model.SingleMovieModel;
import in.itechvalley.topmoviesoffline.viewmodel.TopMoviesViewModel;

public class MainActivity extends AppCompatActivity
{
    @BindView(R.id.swipe_refresh_activity_main)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.recycler_activity_main)
    RecyclerView recyclerView;

    /*
    * Global Instance of ViewModel associated with this Activity
    * */
    private TopMoviesViewModel viewModel;

    /*
    * Global Instance of Adapter required by RecyclerView
    * */
    private TopMoviesRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        * Bind ButterKnife to this Activity
        * */
        ButterKnife.bind(this);

        /*
        * Init the Global Instance of ViewModel
        * */
        viewModel = ViewModelProviders.of(this).get(TopMoviesViewModel.class);

        /*
         * Update UI with Movie List
         * */
        viewModel.requestMovieList();

        initAdapter();
    }

    private void initAdapter()
    {
        /*
        * List Containing Movies
        * */
        List<SingleMovieModel> moviesList = new ArrayList<>();

        /*
        * Init Adapter of RecyclerView
        * */
        adapter = new TopMoviesRecyclerAdapter(moviesList);

        /*
        * Init RecyclerView and attach adapter to RecyclerView
        * */
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        /*
        * Observe MovieList till this LifecycleOwner is alive
        * */
        viewModel.observeMovieList().observe(this, new Observer<List<SingleMovieModel>>()
        {
            @Override
            public void onChanged(List<SingleMovieModel> movieListFromSomewhere)
            {
                if (movieListFromSomewhere != null)
                {
                    Toast.makeText(MainActivity.this, String.format("Total %d movies loaded", movieListFromSomewhere.size()), Toast.LENGTH_SHORT).show();

                    if (movieListFromSomewhere.size() > 0)
                    {
                        adapter.refreshRecyclerView(movieListFromSomewhere);
                    }
                    else
                    {
                        // Hide RecyclerView and Load Error Message
                    }
                }
            }
        });
    }
}
