package com.appinc.movieapp.data.dataSources

import com.appinc.movieapp.data.model.Movie
import com.appinc.movieapp.data.network.MovieResponse

interface MovieDataSource {

    suspend fun fetchPopularMovie() : MovieResponse?

    suspend fun findMovieById(movieId: Int): Movie?
}