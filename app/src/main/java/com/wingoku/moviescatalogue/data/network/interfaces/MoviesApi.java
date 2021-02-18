package com.wingoku.moviescatalogue.data.network.interfaces;

import androidx.lifecycle.LiveData;

import com.wingoku.moviescatalogue.data.network.models.MoviesDetailsDTO;
import com.wingoku.moviescatalogue.data.network.models.MoviesListDTO;
import com.wingoku.moviescatalogue.data.network.models.responses.ApiResponse;

import java.util.List;

import retrofit2.http.GET;

public interface MoviesApi {

    @GET("movie-offers")
    LiveData<ApiResponse<List<MoviesListDTO>>> getMoviesList();

    @GET("movie-data")
    LiveData<ApiResponse<List<MoviesDetailsDTO>>> getMoviesData();
}
