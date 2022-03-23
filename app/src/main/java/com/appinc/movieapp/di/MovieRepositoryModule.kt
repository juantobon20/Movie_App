package com.appinc.movieapp.di

import com.appinc.movieapp.data.dataSources.LocalMovieDataSource
import com.appinc.movieapp.data.dataSources.RemoteMovieDataSource
import com.appinc.movieapp.data.repository.MovieRepository
import com.appinc.movieapp.data.repository.MovieRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieRepositoryModule {

    //Proveer Retrofit
    @Singleton
    @Provides
    fun providerMovieRepository(
        remoteMovieDataSource: RemoteMovieDataSource,
        localMovieDataSource: LocalMovieDataSource
    ): MovieRepository {
        return MovieRepositoryImp(remoteMovieDataSource, localMovieDataSource)
    }
}