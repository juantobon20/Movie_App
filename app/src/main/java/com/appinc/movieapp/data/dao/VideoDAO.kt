package com.appinc.movieapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.appinc.movieapp.data.model.Video

@Dao
interface VideoDAO {

    @Query("Select * From Video Where MovieId = :movieId")
    suspend fun getVideos(movieId: Int): List<Video>

    @Query("Select * From Video Where Id = :videoId")
    suspend fun getVideo(videoId: Int): Video

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideo(video: Video)
}
