package com.appinc.movieapp.data.dataSources

import com.appinc.movieapp.data.dao.MovieDAO
import com.appinc.movieapp.data.model.Movie
import com.appinc.movieapp.data.network.MovieResponse
import javax.inject.Inject

class LocalMovieDataSource @Inject constructor(private val movieDAO: MovieDAO) : MovieDataSource {
    override suspend fun fetchPopularMovie(): MovieResponse? = null

    override suspend fun findMovieById(movieId: Int): Movie? = movieDAO.getMovieId(movieId)
}