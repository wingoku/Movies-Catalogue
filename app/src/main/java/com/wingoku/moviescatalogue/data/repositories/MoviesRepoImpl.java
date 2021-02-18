package com.wingoku.moviescatalogue.data.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.wingoku.moviescatalogue.data.network.interfaces.MoviesApi;
import com.wingoku.moviescatalogue.data.network.models.MoviesDetailsListDTO;
import com.wingoku.moviescatalogue.data.network.models.MoviesOfferListDTO;
import com.wingoku.moviescatalogue.data.network.models.responses.ApiResponse;
import com.wingoku.moviescatalogue.data.network.utils.NetworkBoundResource;
import com.wingoku.moviescatalogue.data.network.utils.Resource;
import com.wingoku.moviescatalogue.data.persistance.daos.MoviesDao;
import com.wingoku.moviescatalogue.domain.interfaces.MoviesRepo;
import com.wingoku.moviescatalogue.domain.mappers.MovieDetailsDomainMapper;
import com.wingoku.moviescatalogue.domain.mappers.MovieOffersDomainMapper;
import com.wingoku.moviescatalogue.domain.models.MovieDetails;
import com.wingoku.moviescatalogue.domain.models.MovieOffer;

import java.util.List;

import javax.inject.Inject;

public class MoviesRepoImpl implements MoviesRepo {
    private static final String TAG = "MoviesRepoImpl";
    private MoviesApi moviesApi;
    private MoviesDao moviesDao;
    private MovieDetailsDomainMapper movieDetailsDomainMapper;
    private MovieOffersDomainMapper movieOffersDomainMapper;

    public MoviesRepoImpl(MoviesApi moviesApi, MoviesDao moviesDao, MovieDetailsDomainMapper movieDetailsDomainMapper, MovieOffersDomainMapper movieOffersDomainMapper) {
        this.moviesApi = moviesApi;
        this.moviesDao = moviesDao;
        this.movieDetailsDomainMapper = movieDetailsDomainMapper;
        this.movieOffersDomainMapper = movieOffersDomainMapper;
    }

    @Override
    public LiveData<Resource<List<MovieDetails>>> getMoviesDetails(int pageNum) {
        Log.d(TAG, "getMoviesDetails()");
        return new NetworkBoundResource<List<MovieDetails>, MoviesDetailsListDTO>() {
            @Override
            protected List<MovieDetails> mapToDomainObject(MoviesDetailsListDTO dto) {
                return movieDetailsDomainMapper.fromDTO(dto.getMovieData());
            }

            @Override
            protected void saveCallResult(@NonNull List<MovieDetails> item) {
                Log.d(TAG, "saveCallResult()");
                if(item == null || item.size() != 0) {
                    Log.d(TAG, "saveCallResult: saving in db");
                    moviesDao.insertMoviesDetails(item.toArray(new MovieDetails[item.size()]));
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<MovieDetails> data) {
                /**
                 * TODO:
                 * If the data is more than a certain period old Eg. a couple of days old than refetch the data from network otherwise just return locally stored data
                 * In this demo project since data on the server will never change, we just read data from Room if it's already in database
                 */

                Log.d(TAG, "shouldFetch() movies : "+ (data == null || data.size() == 0));
                return data == null || data.size() == 0;
            }

            @NonNull
            @Override
            protected LiveData<List<MovieDetails>> loadFromDb() {
                return moviesDao.getMoviesDetails();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<MoviesDetailsListDTO>> createCall() {
                return moviesApi.getMoviesData();
            }
        }.getAsLiveData();
    }

    @Override
    public LiveData<Resource<List<MovieOffer>>> getMoviesOffers(int pageNum) {
        Log.d(TAG, "getMoviesOffers()");
        return new NetworkBoundResource<List<MovieOffer>, MoviesOfferListDTO>() {
            @Override
            protected List<MovieOffer> mapToDomainObject(MoviesOfferListDTO dto) {
                movieOffersDomainMapper.setBaseUrl(dto.getImageBase());
                return movieOffersDomainMapper.fromDTO(dto.getMovieOffers());
            }

            @Override
            protected void saveCallResult(@NonNull List<MovieOffer> item) {
                Log.d(TAG, "saveCallResult()");
                if(item == null || item.size() != 0) {
                    Log.d(TAG, "saveCallResult: saving in db");
                    moviesDao.insertMoviesOffers(item.toArray(new MovieOffer[item.size()]));
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable List<MovieOffer> data) {
                /**
                 * TODO:
                 * If the data is more than a certain period old Eg. a couple of days old than refetch the data from network otherwise just return locally stored data
                 * In this demo project since data on the server will never change, we just read data from Room if it's already in database
                 */

                Log.d(TAG, "shouldFetch() movies : "+ (data == null || data.size() == 0));
                return data == null || data.size() == 0;
            }

            @NonNull
            @Override
            protected LiveData<List<MovieOffer>> loadFromDb() {
                return moviesDao.getMoviesOffers();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<MoviesOfferListDTO>> createCall() {
                return moviesApi.getMoviesOffersList();
            }
        }.getAsLiveData();
    }
}
