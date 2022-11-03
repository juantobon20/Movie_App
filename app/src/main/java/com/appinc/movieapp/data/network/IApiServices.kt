package com.appinc.movieapp.data.network

import com.appinc.movieapp.APIKEY
import com.appinc.movieapp.data.model.Movie
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IApiServices {

    @GET("movie/popular")
    suspend fun getPopularMovie(@Query("api_key") apiKey: String = APIKEY): MovieResponse

    @GET("movie/{id}")
    suspend fun getMovie(@Path("id") id: Int, @Query("api_key") apiKey: String = APIKEY): Movie

    @GET("movie/{movieId}/videos")
    suspend fun getVideos(@Path("movieId") movieId: Int, @Query("api_key") apiKey: String = APIKEY): VideoResponse
}
