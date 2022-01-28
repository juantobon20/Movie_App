package com.appinc.movieapp.domain

import com.appinc.movieapp.data.dao.MovieDAO
import com.appinc.movieapp.data.model.Movie
import javax.inject.Inject

class SaveMovieUseCase @Inject constructor(private val movieDAO: MovieDAO) {

    suspend operator fun invoke(movie: Movie) {
        movieDAO.insertMovie(movie)
    }
}