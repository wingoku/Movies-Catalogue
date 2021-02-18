package com.wingoku.moviescatalogue.di.modules;

import com.wingoku.moviescatalogue.domain.mappers.MovieDetailsDomainMapper;
import com.wingoku.moviescatalogue.domain.mappers.MovieOffersDomainMapper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
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
