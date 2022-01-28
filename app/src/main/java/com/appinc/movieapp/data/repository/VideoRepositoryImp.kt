package com.appinc.movieapp.data.repository

import com.appinc.movieapp.data.model.Video
import com.appinc.movieapp.data.network.ApiServices
import javax.inject.Inject

class VideoRepositoryImp @Inject constructor(private val api: ApiServices) : VideoRepository {

    override suspend fun getVideos(movieId: Int): List<Video> = api.getVideos(movieId).results
}