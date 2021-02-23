package com.wingoku.moviescatalogue.di.modules;

import android.content.Context;

import androidx.room.Room;

import com.wingoku.moviescatalogue.data.persistance.daos.MoviesDao;
import com.wingoku.moviescatalogue.data.persistance.databases.MoviesDatabase;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import me.eugeniomarletti.kotlin.metadata.shadow.javax.inject.Singleton;

@Module
@InstallIn(SingletonComponent.class)
public class MoviesDBModule {
    private static final String DATABASE_NAME = "movies_database";

    @Provides
    @Singleton
    public MoviesDatabase providesMoviesDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(
                context.getApplicationContext(),
                MoviesDatabase.class,
                DATABASE_NAME
        ).build();
    }

    @Provides
    public MoviesDao providesMoviesDao(MoviesDatabase database) {
        return database.getDao();
    }
}
