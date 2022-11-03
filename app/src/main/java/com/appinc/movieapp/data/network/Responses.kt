package com.appinc.movieapp.data.network

import com.appinc.movieapp.data.model.Movie
import com.appinc.movieapp.data.model.Video
import com.google.gson.annotations.SerializedName
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
data class MovieResponse @Inject constructor(
    @SerializedName("results") val results: List<Movie>
)

@Singleton
data class VideoResponse @Inject constructor(
    @SerializedName("results") val results: List<Video>
)
