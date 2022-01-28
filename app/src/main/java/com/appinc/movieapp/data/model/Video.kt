package com.appinc.movieapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import javax.inject.Inject
import javax.inject.Singleton

@Entity(tableName = "Video")
data class Video(
    @PrimaryKey @SerializedName("id") val id: String,
    @SerializedName("key") val key: String,
    var movieId: Int
)