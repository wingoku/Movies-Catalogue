package com.wingoku.moviescatalogue.data.network.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.wingoku.moviescatalogue.data.network.utils.Resource;
import com.wingoku.moviescatalogue.domain.interfaces.MoviesRepo;
import com.wingoku.moviescatalogue.domain.models.MovieDetails;
import com.wingoku.moviescatalogue.domain.models.MovieOffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FakeMoviesRepo implements MoviesRepo {

    private MutableLiveData<Resource<List<MovieOffer>>> movieOfferLiveData;
    private MutableLiveData<Resource<List<MovieDetails>>> movieDetailsLiveData;
    private List<MovieOffer> movieOffers;
    private List<MovieDetails> movieDetails;

    public FakeMoviesRepo() {
        movieOffers = Arrays.asList(
                new MovieOffer(1, "100", "/interstellar.jpg", true, "https://google.com"),
                new MovieOffer(1, "101", "/wolf.jpg", true, "https://google.com"),
                new MovieOffer(1, "102", "/southPar.jpg", true, "https://google.com")
        );

        movieDetails = Arrays.asList(
                new MovieDetails(1, "Interstellar", "Epic Movie"),
                new MovieDetails(2, "Wolf Of Wall Street", "Them Stonks"),
                new MovieDetails(3, "South Park", "Comedy Series")
        );
    }

    public void insertDummyMovieDetails_success() {
        Resource<List<MovieDetails>> resource = Resource.success(movieDetails);
        movieDetailsLiveData.setValue(resource);
    }

    public void insertDummyMovieOffers_success() {
        Resource<List<MovieOffer>> resource = Resource.success(movieOffers);
        movieOfferLiveData.setValue(resource);
    }

    public void insertDummyMovieDetails_error() {
        Resource<List<MovieDetails>> resource = Resource.error("Some Error", new ArrayList<>());
        movieDetailsLiveData.setValue(resource);
    }

    public void insertDummyMovieOffers_error() {
        Resource<List<MovieOffer>> resource = Resource.error("Some Error", new ArrayList<>());
        movieOfferLiveData.setValue(resource);
    }

    @Override
    public LiveData<Resource<List<MovieDetails>>> getMoviesDetails(int pageNum) {
        return movieDetailsLiveData;
    }

    @Override
    public LiveData<Resource<List<MovieOffer>>> getMoviesOffers(int pageNum) {
        return movieOfferLiveData;
    }
}
