package com.wingoku.moviescatalogue.presentation.adapters.recyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.wingoku.moviescatalogue.R;
import com.wingoku.moviescatalogue.domain.models.MovieItemDetails;
import com.wingoku.moviescatalogue.domain.models.MovieItemDetails;
import com.wingoku.moviescatalogue.presentation.interfaces.OnItemClickListener;

public class MoviesListAdapter extends ListAdapter<MovieItemDetails, MoviesListAdapter.MoviesListRecyclerViewHolder> {
    private OnItemClickListener<MovieItemDetails> itemClickListener;

    public MoviesListAdapter(OnItemClickListener<MovieItemDetails> listener) {
        super(DIFF_CALLBACK);
        itemClickListener = listener;
    }

    @Override
    public MoviesListRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.layout_item_card, parent, false);

        return new MoviesListRecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MoviesListRecyclerViewHolder holder, int position) {
        holder.bind(getItem(position), itemClickListener);
    }

    static class MoviesListRecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView movieNameTV;
        TextView moviePriceTV;
        ImageView imageView;

        public MoviesListRecyclerViewHolder(View itemView) {
            super(itemView);
            movieNameTV = itemView.findViewById(R.id.tv_name);
            moviePriceTV = itemView.findViewById(R.id.tv_desc);
            imageView = itemView.findViewById(R.id.imageView);
        }

        public void bind(final MovieItemDetails movie, final OnItemClickListener<MovieItemDetails> listener) {
            movieNameTV.setText(movie.getMovieName());
            moviePriceTV.setText(movie.getMovieDescription());
            Picasso.get()
                    .load(R.drawable.place_holder)
                    .into(imageView);
            
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(movie);
                }
            });
        }
    }

    private static final DiffUtil.ItemCallback<MovieItemDetails> DIFF_CALLBACK = new DiffUtil.ItemCallback<MovieItemDetails>() {
        @Override
        public boolean areItemsTheSame(@NonNull MovieItemDetails oldItem, @NonNull MovieItemDetails newItem) {
            return oldItem.getMovieId() == newItem.getMovieId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull MovieItemDetails oldItem, @NonNull MovieItemDetails newItem) {
            return oldItem.getMovieName().equals(newItem.getMovieName());
        }
    };
}
