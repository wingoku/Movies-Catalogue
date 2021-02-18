package com.wingoku.moviescatalogue.data.persistance.databases;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.wingoku.moviescatalogue.data.persistance.converters.ListConverter;
import com.wingoku.moviescatalogue.data.persistance.daos.MoviesDao;
import com.wingoku.moviescatalogue.domain.models.MovieDetails;
import com.wingoku.moviescatalogue.domain.models.MovieOffer;

@Database(entities = {MovieOffer.class, MovieDetails.class}, version = 1, exportSchema = false)
@TypeConverters({ListConverter.class})
public abstract class MoviesDatabase extends RoomDatabase {
    public abstract MoviesDao getDao();
}
