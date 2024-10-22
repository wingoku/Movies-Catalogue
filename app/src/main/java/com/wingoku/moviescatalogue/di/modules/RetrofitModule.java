package com.wingoku.moviescatalogue.di.modules;

import com.wingoku.moviescatalogue.BuildConfig;
import com.wingoku.moviescatalogue.data.network.adapters.LiveDataAdapterFactory;
import com.wingoku.moviescatalogue.data.network.interfaces.MoviesApi;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import me.eugeniomarletti.kotlin.metadata.shadow.javax.inject.Singleton;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = {OKHttpModule.class})
@InstallIn(SingletonComponent.class)
public class RetrofitModule {

    @Provides
    @Singleton
    public MoviesApi providesMoviesAPI(Retrofit retrofit) {
        return retrofit.create(MoviesApi.class);
    }

    @Provides
    @Singleton
    public Retrofit providesRetrofit(OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory, LiveDataAdapterFactory liveDataAdapterFactory) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(liveDataAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .baseUrl(BuildConfig.API_URL)
                .build();
    }

    @Provides
    public GsonConverterFactory providesGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    public LiveDataAdapterFactory providesCallToLiveDataAdapterFactory() {
        return new LiveDataAdapterFactory();
    }
}
