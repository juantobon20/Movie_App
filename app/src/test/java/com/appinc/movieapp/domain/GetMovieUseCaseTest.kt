package com.appinc.movieapp.domain

import com.appinc.movieapp.data.dao.MovieDAO
import com.appinc.movieapp.data.repository.MovieRepository
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before

@ExperimentalCoroutinesApi
class GetMovieUseCaseTest {

    private val movieRepository = mockk<MovieRepository>(relaxed = true)
    private val movieDAO = mockk<MovieDAO>(relaxed = true)

    private lateinit var getMovieUseCase: GetMovieUseCase

    @Before
    fun setup() {
        getMovieUseCase = GetMovieUseCase(movieRepository, movieDAO)
    }

    /*@Test
    fun `get movie from remote when is not found in local storage`() {
        val expected = mockk<Movie>()

        coEvery {
            movieRepository.getMovie(1)
        } answers {
            expected
        }

        val response = runBlocking { getMovieUseCase(1) }


        Assert.assertEquals(expected, response)
    }*/
}
