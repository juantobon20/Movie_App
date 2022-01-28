package com.appinc.movieapp.views.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appinc.movieapp.data.model.Movie
import com.appinc.movieapp.domain.GetMovieUseCase
import com.appinc.movieapp.util.IOnClickListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getMovieUseCase: GetMovieUseCase) : ViewModel(), IOnClickListener<Movie> {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    private val _movieId = MutableLiveData<Int>()
    val movieId: LiveData<Int> get() = _movieId

    init {
        this.onLoadMovies()
    }

    private fun onLoadMovies() {
        viewModelScope.launch {
            this@MainViewModel._movies.postValue(getMovieUseCase())
        }
    }

    override fun onClick(t: Movie) {
        this._movieId.postValue(t.id)
    }
}