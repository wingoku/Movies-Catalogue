package com.wingoku.moviescatalogue.data.network.interfaces;

import androidx.lifecycle.LiveData;

import com.wingoku.moviescatalogue.data.network.models.MoviesDetailsListDTO;
import com.wingoku.moviescatalogue.data.network.models.MoviesOfferListDTO;
import com.wingoku.moviescatalogue.data.network.models.responses.ApiResponse;

import retrofit2.http.GET;

public interface MoviesApi {

    @GET("movie-offers")
    LiveData<ApiResponse<MoviesOfferListDTO>> getMoviesOffersList();

    @GET("movie-data")
    LiveData<ApiResponse<MoviesDetailsListDTO>> getMoviesData();
}
