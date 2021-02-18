package com.wingoku.moviescatalogue.di.modules;

import android.content.Context;

import androidx.room.Room;

import com.wingoku.moviescatalogue.data.persistance.databases.MoviesDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MoviesDBModule {
    private static final String DATABASE_NAME = "movies_database";

    @Provides
    @Singleton
    public MoviesDatabase providesMoviesDatabase(Context context) {
        return Room.databaseBuilder(
                context.getApplicationContext(),
                MoviesDatabase.class,
                DATABASE_NAME
        ).build();
    }
}
