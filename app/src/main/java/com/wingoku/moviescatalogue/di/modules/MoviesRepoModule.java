package com.wingoku.moviescatalogue.di.modules;

import com.wingoku.moviescatalogue.data.network.interfaces.MoviesApi;
import com.wingoku.moviescatalogue.data.persistance.daos.MoviesDao;
import com.wingoku.moviescatalogue.data.repositories.MoviesRepoImpl;
import com.wingoku.moviescatalogue.domain.interfaces.MoviesRepo;
import com.wingoku.moviescatalogue.domain.mappers.MovieDetailsDomainMapper;
import com.wingoku.moviescatalogue.domain.mappers.MovieOffersDomainMapper;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import me.eugeniomarletti.kotlin.metadata.shadow.javax.inject.Singleton;

@Module(includes = {RetrofitModule.class, MoviesDBModule.class, MapperModule.class})
@InstallIn(ViewModelComponent.class)
public class MoviesRepoModule {

    @Provides
    @Singleton
    public MoviesRepo providesMoviesRepo(MoviesApi moviesApi, MoviesDao moviesDao, MovieDetailsDomainMapper movieDetailsDomainMapper, MovieOffersDomainMapper movieOffersDomainMapper) {
        return new MoviesRepoImpl(moviesApi, moviesDao, movieDetailsDomainMapper, movieOffersDomainMapper);
    }
}
