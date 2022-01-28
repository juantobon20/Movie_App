package com.appinc.movieapp.domain

import com.appinc.movieapp.data.model.Video
import com.appinc.movieapp.data.repository.VideoRepositoryImp
import javax.inject.Inject

class GetVideoUseCase @Inject constructor(
    private val repositoryImp: VideoRepositoryImp,
    private val saveVideoUseCase: SaveVideoUseCase
) {

    suspend operator fun invoke(movieId: Int): List<Video> {
      val videos = repositoryImp.getVideos(movieId)
      videos.forEach { it.movieId = movieId; saveVideoUseCase.invoke(it) }
      return videos
    }
}