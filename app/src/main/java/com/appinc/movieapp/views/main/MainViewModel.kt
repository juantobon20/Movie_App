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

    private val _isFilter = MutableLiveData(true)
    val isFilter: LiveData<Boolean> get() = _isFilter

    private var moviesAll: List<Movie> = emptyList()

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    private val _movieId = MutableLiveData<Int>()
    val movieId: LiveData<Int> get() = _movieId

    init {
        this.onLoadMovies()
    }

    fun onLoadMovies() {
        viewModelScope.launch {
            val movies = getMovieUseCase()
            this@MainViewModel._movies.postValue(movies)
            this@MainViewModel.moviesAll = movies
        }
    }

    fun onFilter() {
        val filterAct = !this.isFilter.value!!
        this._isFilter.postValue(filterAct)

        val movies = if (!filterAct) moviesAll.filter { it.voteCount > 2000 } else moviesAll
        this._movies.postValue(movies)
    }

    fun onFilterFavorite() {
        val movies = moviesAll.filter { it.favorite }
        this._movies.postValue(movies)
    }

    override fun onClick(t: Movie) {
        this._movieId.postValue(t.id)
    }
}
