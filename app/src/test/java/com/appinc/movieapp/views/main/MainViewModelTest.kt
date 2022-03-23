package com.appinc.movieapp.views.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.appinc.movieapp.MainCoroutineRule
import com.appinc.movieapp.data.model.Movie
import com.appinc.movieapp.domain.GetMovieUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule(testDispatcher)

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val getMovieUseCase = mockk<GetMovieUseCase>()
    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        viewModel = MainViewModel(getMovieUseCase)
    }

    @Test
    fun `when getMovie is called then should modify liveData`() {
        val movies = mockk<List<Movie>>()
        coEvery {
            getMovieUseCase()
        } answers {
            movies
        }

        viewModel.onLoadMovies()

        coVerify { getMovieUseCase() }
        assertEquals(movies, viewModel.movies.value)
    }
}