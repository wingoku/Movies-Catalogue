package com.wingoku.moviescatalogue.presentation.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wingoku.moviescatalogue.R;
import com.wingoku.moviescatalogue.data.network.utils.Resource;
import com.wingoku.moviescatalogue.domain.models.MovieItemDetails;
import com.wingoku.moviescatalogue.domain.viewModels.MovieListViewModel;
import com.wingoku.moviescatalogue.domain.viewModels.SharedViewModel;
import com.wingoku.moviescatalogue.presentation.activities.MainActivity;
import com.wingoku.moviescatalogue.presentation.adapters.recyclerView.MoviesListAdapter;
import com.wingoku.moviescatalogue.presentation.interfaces.OnItemClickListener;

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
        initSharedViewModel();
    }

    public void initSharedViewModel() {
        SharedViewModel sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        MovieListViewModel movieListViewModel = new ViewModelProvider(requireActivity()).get(MovieListViewModel.class);

        movieListViewModel.observeMovieDetailsSource(sharedViewModel.getMoviesListData());
        movieListViewModel.getMovieItemsDetails().observe(getViewLifecycleOwner(), new Observer<Resource<List<MovieItemDetails>>>() {
            @Override
            public void onChanged(Resource<List<MovieItemDetails>> listResource) {
                Log.d(TAG, "onChanged: data source status "+ listResource.status.name());
                MainActivity activity = ((MainActivity)requireActivity());
                if(listResource.status == Resource.Status.LOADING) {
                    activity.showProgressBar();
                }
                else {
                    activity.hideProgressBar();
                    adapter.submitList(listResource.data);
                }
            }
        });
    }

    private void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        //passing item click listener to cat adapter cuz it's cleaner and best practice: https://stackoverflow.com/questions/49969278/recyclerview-item-click-listener-the-right-way
        adapter = new MoviesListAdapter(getOnItemClickListener());
        recyclerView.setAdapter(adapter);
    }

    private OnItemClickListener<MovieItemDetails> getOnItemClickListener() {
        return new OnItemClickListener<MovieItemDetails>() {
            @Override
            public void onItemClick(MovieItemDetails item) {
                navController.navigate(MoviesListFragmentDirections.actionMoviesListFragmentToMovieItemDetailsFragment(item));
            }
        };
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }
}
