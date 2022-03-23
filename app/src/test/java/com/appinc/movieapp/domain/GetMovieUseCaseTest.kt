package com.appinc.movieapp.domain

import com.appinc.movieapp.data.dao.MovieDAO
import com.appinc.movieapp.data.model.Movie
import com.appinc.movieapp.data.repository.MovieRepository
import com.nhaarman.mockitokotlin2.mock
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetMovieUseCaseTest {

    private val movieRepository = mockk<MovieRepository>(relaxed = true)
    private val movieDAO = mock<MovieDAO>()

    private lateinit var getMovieUseCase: GetMovieUseCase

    @Before
    fun setup() {
        getMovieUseCase = GetMovieUseCase(movieRepository, movieDAO)
    }

    @Test
    fun `get movie from remote when is not found in local storage`() {
        val expected = mockk<Movie>()

        coEvery {
            movieRepository.getMovie(1)
        } answers {
            expected
        }

        val response = runBlocking { getMovieUseCase(1) }

        coVerify(exactly = 1) {
            movieRepository.getMovie(1)
        }


        Assert.assertEquals(expected, response)
    }
}