package com.appinc.movieapp.domain

import com.appinc.movieapp.data.dao.VideoDAO
import com.appinc.movieapp.data.model.Video
import javax.inject.Inject

class SaveVideoUseCase @Inject constructor(private val videoDAO: VideoDAO) {

    suspend operator fun invoke(video: Video) {
        videoDAO.insertVideo(video)
    }
}