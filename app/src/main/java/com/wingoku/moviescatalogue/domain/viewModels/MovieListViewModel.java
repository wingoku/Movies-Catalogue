package com.wingoku.moviescatalogue.domain.viewModels;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.wingoku.moviescatalogue.data.network.utils.Resource;
import com.wingoku.moviescatalogue.domain.interfaces.MoviesRepo;
import com.wingoku.moviescatalogue.domain.models.MovieDetails;
import com.wingoku.moviescatalogue.domain.models.MovieItemDetails;
import com.wingoku.moviescatalogue.domain.models.MovieOffer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MovieListViewModel extends ViewModel {
    private static final String TAG = "MovieListViewModel";
    private MoviesRepo repo;
    private MediatorLiveData<Resource<List<MovieItemDetails>>> mediatorLiveData;
    private HashMap<Integer, MovieItemDetails> movieItemDetailsMap;
    private List<MovieItemDetails> movieItemDetailsList;
    private int receivedSourceCount;
    private Resource<MovieItemDetails> resource1;
    private Resource<MovieItemDetails> resource2;

    @Inject
    public MovieListViewModel(MoviesRepo repo) {
        this.repo = repo;
        movieItemDetailsMap = new HashMap<>();
        movieItemDetailsList = new ArrayList<>();
        mediatorLiveData = new MediatorLiveData<>();

        receivedSourceCount = 0;

        observeMovieOffersSource(repo.getMoviesOffers(1));
    }

    public void observeMovieDetailsSource(LiveData<Resource<List<MovieDetails>>> movieDetailsLiveData) {
        mediatorLiveData.addSource(movieDetailsLiveData, new Observer<Resource<List<MovieDetails>>>() {
            @Override
            public void onChanged(Resource<List<MovieDetails>> listResource) {
                switch (listResource.status) {
                    case LOADING:
                        mediatorLiveData.postValue(Resource.loading(null));
                        break;

                    case SUCCESS:
                        receivedSourceCount++;
                        mediatorLiveData.removeSource(movieDetailsLiveData);
                        populateMovieDetailsDataInItemDetails(listResource.data);

                        resource2 = Resource.success(null);
                        break;

                    case ERROR:
                        receivedSourceCount++;
                        mediatorLiveData.removeSource(movieDetailsLiveData);
                        resource2 = Resource.error(listResource.message, null);
                        Log.e(TAG, "observeMovieDetailsSource :: onChanged: "+ listResource.message);
                        break;
                }

                checkIfAllDataReceived();
            }
        });
    }

    private void observeMovieOffersSource(LiveData<Resource<List<MovieOffer>>> movieListLiveData) {

    }

    public LiveData<Resource<List<MovieItemDetails>>> getMovieItemsDetails() {
        return mediatorLiveData;
    }

    private void populateMovieOfferDataInItemDetails(@NonNull List<MovieOffer> movieOffers) {
        for(MovieOffer movieOffer : movieOffers) {
            movieItemDetailsMap.putIfAbsent(movieOffer.getMovieId(), new MovieItemDetails());
            movieItemDetailsMap.get(movieOffer.getMovieId()).setMovieId(movieOffer.getMovieId());
            movieItemDetailsMap.get(movieOffer.getMovieId()).setMoviePrice(movieOffer.getPrice());
            movieItemDetailsMap.get(movieOffer.getMovieId()).setImageUrl(movieOffer.getImageBase()+movieOffer.getImage());
        }
    }

    private void populateMovieDetailsDataInItemDetails(@NonNull List<MovieDetails> movieDetails) {
        for(MovieDetails movieDetail : movieDetails) {
            movieItemDetailsMap.putIfAbsent(movieDetail.getMovieId(), new MovieItemDetails());
            movieItemDetailsMap.get(movieDetail.getMovieId()).setMovieId(movieDetail.getMovieId());
            movieItemDetailsMap.get(movieDetail.getMovieId()).setMovieName(movieDetail.getTitle());
            movieItemDetailsMap.get(movieDetail.getMovieId()).setMovieDescription(movieDetail.getSubTitle());
        }
    }

    private void checkIfAllDataReceived() {
        if(receivedSourceCount >= 2) {
            movieItemDetailsList.addAll(movieItemDetailsMap.values());
            if(resource1 != null && resource1.status == Resource.Status.SUCCESS && resource2 != null && resource2.status == Resource.Status.SUCCESS) {
                mediatorLiveData.setValue(Resource.success(movieItemDetailsList));
            }
            else {
                mediatorLiveData.setValue(Resource.error("Some data couldn't be fetched", movieItemDetailsList));
            }
        }
    }
}
