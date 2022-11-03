package com.appinc.movieapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.appinc.movieapp.data.dao.MovieDAO
import com.appinc.movieapp.data.dao.VideoDAO
import com.appinc.movieapp.data.model.Movie
import com.appinc.movieapp.data.model.Video

@Database(entities = [Movie::class, Video::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDAO
    abstract fun videoDao(): VideoDAO
}
