package com.wingoku.moviescatalogue.di.modules;

import com.wingoku.moviescatalogue.domain.mappers.MovieDetailsDomainMapper;
import com.wingoku.moviescatalogue.domain.mappers.MovieOffersDomainMapper;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import me.eugeniomarletti.kotlin.metadata.shadow.javax.inject.Singleton;

@Module
@InstallIn(ViewModelComponent.class)
public class MapperModule {
    @Provides
    @Singleton
    public MovieDetailsDomainMapper providesMovieDetailsDomainMapper() {
        return new MovieDetailsDomainMapper();
    }

    @Provides
    @Singleton
    public MovieOffersDomainMapper providesMovieOffersDomainMapper() {
        return new MovieOffersDomainMapper();
    }
}
