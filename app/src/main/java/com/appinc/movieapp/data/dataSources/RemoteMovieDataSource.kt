package com.appinc.movieapp.data.dataSources

import com.appinc.movieapp.data.model.Movie
import com.appinc.movieapp.data.network.ApiServices
import com.appinc.movieapp.data.network.MovieResponse
import javax.inject.Inject

class RemoteMovieDataSource @Inject constructor(private val api: ApiServices) : MovieDataSource  {

    override suspend fun fetchPopularMovie(): MovieResponse = api.getPopularMovie()

    override suspend fun findMovieById(movieId: Int): Movie = api.getMovie(movieId)
}