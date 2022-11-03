package com.appinc.movieapp.data.repository

import com.appinc.movieapp.data.model.Movie

interface MovieRepository {

    suspend fun getPopularMovies(): List<Movie>

    suspend fun getMovie(id: Int): Movie
}
