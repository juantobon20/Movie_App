package com.appinc.movieapp.di

import android.content.Context
import androidx.room.Room
import com.appinc.movieapp.data.dao.MovieDAO
import com.appinc.movieapp.data.dao.VideoDAO
import com.appinc.movieapp.data.database.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideMovieDAO(movieDatabase: MovieDatabase): MovieDAO = movieDatabase.movieDao()

    @Provides
    fun provideVideoDAO(movieDatabase: MovieDatabase): VideoDAO = movieDatabase.videoDao()

    @Provides
    @Singleton
    fun providerMovieDatabase(@ApplicationContext appContext: Context): MovieDatabase =
        Room.databaseBuilder(appContext, MovieDatabase::class.java, "Movie.db").build()
}
