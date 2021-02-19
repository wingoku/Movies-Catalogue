package com.wingoku.moviescatalogue.domain.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.wingoku.moviescatalogue.data.network.utils.Resource;
import com.wingoku.moviescatalogue.domain.interfaces.MoviesRepo;
import com.wingoku.moviescatalogue.domain.models.MovieOffer;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SharedViewModel extends AndroidViewModel {
    @Inject
    MoviesRepo repo;

    private MediatorLiveData<Resource<List<MovieOffer>>> movieOffersMediatorLiveData = new MediatorLiveData<>();

    public SharedViewModel(@NonNull Application application) {
        super(application);
    }

    public void setCategoryData(Resource<List<MovieOffer>> catData) {

        movieOffersMediatorLiveData.setValue(catData);
    }

    public LiveData<Resource<List<MovieOffer>>> getCategoryData() {
        return movieOffersMediatorLiveData;
    }

    public LiveData<Resource<List<MovieOffer>>> returnLiveData() {
        return repo.getMoviesOffers(1);
    }
}
