package com.appinc.movieapp.domain

import com.appinc.movieapp.data.dao.MovieDAO
import com.appinc.movieapp.data.model.Movie
import com.appinc.movieapp.data.repository.MovieRepository
import com.appinc.movieapp.data.repository.MovieRepositoryImp
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import javax.inject.Inject

@HiltAndroidTest
@ExperimentalCoroutinesApi
class GetMovieUseCaseTest {

    @get:Rule
    var hiltrule = HiltAndroidRule(this)

    @Inject
    private lateinit var movieDAO: MovieDAO
    @Inject
    private lateinit var movieRepository: MovieRepositoryImp
    @Inject
    private lateinit var saveMovieUseCase: SaveMovieUseCase

    private lateinit var getMovieUseCase: GetMovieUseCase

    private lateinit var movie: Movie

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {

        /*saveMovieUseCase = mock(SaveMovieUseCase::class.java)
        movieRepository = mock(MovieRepositoryImp::class.java)
        movieDAO = mock(MovieDAO::class.java)*/
        movie = mock(Movie::class.java)

        hiltrule.inject()
        this.getMovieUseCase = GetMovieUseCase(movieRepository, saveMovieUseCase, movieDAO)

        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `get movie from remote when is not found in local storage`() = runBlockingTest {
        //Given
        val movieId = 123445
        whenever(movieDAO.getMovieId(any())).thenReturn(null)
        whenever(movieRepository.getMovie(any())).thenReturn(movie)
        //When
        val movie = getMovieUseCase.invoke(movieId)
        //Then
        verify(saveMovieUseCase).invoke(movie)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}