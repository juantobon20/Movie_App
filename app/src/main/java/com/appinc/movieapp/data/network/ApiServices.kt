package com.appinc.movieapp.data.network

import com.appinc.movieapp.data.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiServices @Inject constructor(private val api: IApiServices) {

    suspend fun getPopularMovie(): MovieResponse = withContext(Dispatchers.IO) { api.getPopularMovie() }

    suspend fun getMovie(id: Int): Movie = withContext(Dispatchers.IO) { api.getMovie(id) }

    suspend fun getVideos(movieId: Int): VideoResponse = withContext(Dispatchers.IO) { api.getVideos(movieId) }
}
