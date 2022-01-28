package com.appinc.movieapp.data.repository

import com.appinc.movieapp.data.model.Video

interface VideoRepository {

    suspend fun getVideos(movieId: Int) : List<Video>
}