package com.wingoku.moviescatalogue.data.persistance.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.wingoku.moviescatalogue.domain.models.MovieDetails;
import com.wingoku.moviescatalogue.domain.models.MovieOffer;

import java.util.List;

import static androidx.room.OnConflictStrategy.IGNORE;

@Dao
public interface MoviesDao {
    @Insert(onConflict = IGNORE)
    long[] insertMoviesDetails(MovieDetails... movieDetails);

    @Insert(onConflict = IGNORE)
    void insertMovieDetails(MovieDetails movieDetails);

    @Insert(onConflict = IGNORE)
    long[] insertMoviesOffers(MovieOffer... movieOffer);

    @Insert(onConflict = IGNORE)
    void insertMovieOffer(MovieOffer movieOffer);

    @Query("SELECT * FROM movie_details")
    LiveData<List<MovieDetails>> getMoviesDetails();

    @Query("SELECT * FROM movie_offer")
    LiveData<List<MovieOffer>> getMoviesOffers();
}
