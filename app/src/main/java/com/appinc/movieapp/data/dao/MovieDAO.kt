package com.appinc.movieapp.data.dao

import androidx.room.*
import com.appinc.movieapp.data.model.Movie

@Dao
interface MovieDAO {

    @Query("Select * From Movie Where Id = :movieId")
    suspend fun getMovieId(movieId: Int): Movie?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie)

    @Query("UPDATE Movie SET Favorite = :isFavorite WHERE Id = :movieId")
    suspend fun updateFavorite(isFavorite: Boolean, movieId: Int)
}