package com.appinc.movieapp.data.repository

import com.appinc.movieapp.data.dataSources.LocalMovieDataSource
import com.appinc.movieapp.data.dataSources.RemoteMovieDataSource
import com.appinc.movieapp.data.model.Movie
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val remoteMovieDataSource: RemoteMovieDataSource,
    private val localMovieDataSource: LocalMovieDataSource
) : MovieRepository {

    override suspend fun getPopularMovies(): List<Movie> =
        remoteMovieDataSource.fetchPopularMovie().results

    override suspend fun getMovie(id: Int): Movie {
        var movie = localMovieDataSource.findMovieById(id)
        if (movie == null) movie = remoteMovieDataSource.findMovieById(id)
        return movie
    }
}
