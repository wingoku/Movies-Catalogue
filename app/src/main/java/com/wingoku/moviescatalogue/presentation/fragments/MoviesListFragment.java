package com.wingoku.moviescatalogue.presentation.fragments;

import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wingoku.moviescatalogue.R;
import com.wingoku.moviescatalogue.data.network.utils.Resource;
import com.wingoku.moviescatalogue.domain.models.MovieDetails;
import com.wingoku.moviescatalogue.domain.models.MovieDetails;
import com.wingoku.moviescatalogue.domain.models.MovieItemDetails;
import com.wingoku.moviescatalogue.domain.models.MovieOffer;
import com.wingoku.moviescatalogue.domain.viewModels.MovieListViewModel;
import com.wingoku.moviescatalogue.domain.viewModels.SharedViewModel;
import com.wingoku.moviescatalogue.presentation.activities.MainActivity;
import com.wingoku.moviescatalogue.presentation.adapters.recyclerView.MoviesListAdapter;
import com.wingoku.moviescatalogue.presentation.interfaces.OnItemClickListener;

import java.util.HashMap;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MoviesListFragment extends BaseFragment {
    private static final String TAG = "MovieOffersFragment";
    private NavController navController;
    private MoviesListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_recycler_view, container, false);
        initRecyclerView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);;
        ((MainActivity)requireActivity()).showToolbar();
    }

    private void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        adapter = new MoviesListAdapter(getOnItemClickListener());
        recyclerView.setAdapter(adapter);
    }

    private OnItemClickListener<MovieItemDetails> getOnItemClickListener() {
        return new OnItemClickListener<MovieItemDetails>() {
            @Override
            public void onItemClick(MovieItemDetails item) {
            }
        };
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }
}
