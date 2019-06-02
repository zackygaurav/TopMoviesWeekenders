package in.itechvalley.topmoviesoffline.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.itechvalley.topmoviesoffline.model.SingleMovieModel;

public class TopMoviesRecyclerAdapter extends RecyclerView.Adapter<TopMoviesRecyclerAdapter.TopMovieViewHolder>
{
    /*
    * List containing all movies
    * */
    private List<SingleMovieModel> movieList;

    /*
    * Constructor
    * */
    public TopMoviesRecyclerAdapter(List<SingleMovieModel> movieList)
    {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public TopMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View singleItemHolder = LayoutInflater
                .from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);

        return new TopMovieViewHolder(singleItemHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull TopMovieViewHolder holder, int position)
    {
        SingleMovieModel singleMovieData = movieList.get(position);

        holder.txtTitle.setText(singleMovieData.getMovieTitle());
    }

    @Override
    public int getItemCount()
    {
        return this.movieList.size();
    }

    public void refreshRecyclerView(List<SingleMovieModel> newMovieList)
    {
        if (this.movieList != null)
        {
            this.movieList.clear();
            this.movieList.addAll(newMovieList);
        }
        else
        {
            this.movieList = newMovieList;
        }

        notifyDataSetChanged();
    }

    class TopMovieViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(android.R.id.text1)
        TextView txtTitle;

        /*
        * Constructor
        * */
        public TopMovieViewHolder(@NonNull View itemView)
        {
            super(itemView);

            /*
            * ButterKnife
            * */
            ButterKnife.bind(this, itemView);
        }
    }
}
