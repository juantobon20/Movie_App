package com.appinc.movieapp.domain

import com.appinc.movieapp.data.dao.MovieDAO
import com.appinc.movieapp.data.model.Movie
import com.appinc.movieapp.data.repository.MovieRepository
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    // private val saveMovieUseCase: SaveMovieUseCase,
    private val movieDAO: MovieDAO
) {

    suspend operator fun invoke(): List<Movie> = movieRepository.getPopularMovies()

    suspend operator fun invoke(movieId: Int): Movie {
        var movie = movieDAO.getMovieId(movieId)
        if (movie == null) {
            movie = movieRepository.getMovie(movieId)
            // saveMovieUseCase(movie)
        }
        return movie
    }
}
