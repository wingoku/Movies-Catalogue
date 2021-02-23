package com.wingoku.moviescatalogue.domain.interfaces;

import androidx.lifecycle.LiveData;

import com.wingoku.moviescatalogue.data.network.utils.Resource;
import com.wingoku.moviescatalogue.domain.models.MovieDetails;
import com.wingoku.moviescatalogue.domain.models.MovieOffer;

import java.util.List;

public interface MoviesRepo {
    LiveData<Resource<List<MovieDetails>>> getMoviesDetails(int pageNum);
    LiveData<Resource<List<MovieOffer>>> getMoviesOffers(int pageNum);
}
