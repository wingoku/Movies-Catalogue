package com.wingoku.moviescatalogue.domain.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.wingoku.moviescatalogue.data.network.utils.Resource;
import com.wingoku.moviescatalogue.domain.interfaces.MoviesRepo;
import com.wingoku.moviescatalogue.domain.models.MovieDetails;
import com.wingoku.moviescatalogue.domain.models.MovieDetails;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.EntryPoint;
import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SharedViewModel extends ViewModel {
    private MoviesRepo repo;
    private MediatorLiveData<Resource<List<MovieDetails>>> movieOffersMediatorLiveData = new MediatorLiveData<>();

    @Inject
    public SharedViewModel(MoviesRepo repo) {
        this.repo = repo;
    }

    public void setMoviesListData(Resource<List<MovieDetails>> catData) {
        movieOffersMediatorLiveData.setValue(catData);
    }

    public LiveData<Resource<List<MovieDetails>>> getMoviesListData() {
        return movieOffersMediatorLiveData;
    }

    public LiveData<Resource<List<MovieDetails>>> fetchMoviesListData() {
        return repo.getMoviesDetails(1);
    }
}
