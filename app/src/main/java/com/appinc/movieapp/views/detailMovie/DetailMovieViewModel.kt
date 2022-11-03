package com.appinc.movieapp.views.detailMovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appinc.movieapp.data.dao.MovieDAO
import com.appinc.movieapp.data.dao.VideoDAO
import com.appinc.movieapp.data.model.Movie
import com.appinc.movieapp.data.model.Video
import com.appinc.movieapp.domain.GetMovieUseCase
import com.appinc.movieapp.domain.GetVideoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase,
    private val getVideoUseCase: GetVideoUseCase,
    private val movieDAO: MovieDAO,
    private val videoDAO: VideoDAO
) : ViewModel() {

    private val _videos = MutableLiveData<List<Video>>()
    val videos: LiveData<List<Video>> get() = _videos

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> get() = _movie

    fun onLoadDetailMovie(movieId: Int) {
        viewModelScope.launch {
            this@DetailMovieViewModel._movie.postValue(getMovieUseCase(movieId))
            this@DetailMovieViewModel._videos.postValue(getVideoUseCase(movieId))
        }
    }

    fun setFavorite() {
        viewModelScope.launch {
            try {
                val movie = this@DetailMovieViewModel.movie.value!!
                movie.favorite = !movie.favorite
                movieDAO.updateFavorite(movie.favorite, movie.id)
                this@DetailMovieViewModel._movie.postValue(movie)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    private suspend fun insertVideos(videos: MutableList<Video>, movieId: Int) {
        try {
            videos.forEach { it.movieId = movieId; videoDAO.insertVideo(it) }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}
