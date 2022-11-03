package com.appinc.movieapp.views.detailMovie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.appinc.movieapp.databinding.ActivityDetailMovieBinding
import dagger.hilt.android.AndroidEntryPoint

const val MOVIE_ID_KEY = "movieId"

@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {

    private var _binding: ActivityDetailMovieBinding? = null
    private lateinit var viewModel: DetailMovieViewModel

    private val adapter: DetailMovieAdapter by lazy { DetailMovieAdapter() }

    private val movieId: Int by lazy {
        intent.extras?.takeIf { it.containsKey(MOVIE_ID_KEY) }?.apply {
            return@lazy getInt(MOVIE_ID_KEY)
        }
        0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this._binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setSupportActionBar(this._binding?.toolbar)
        setContentView(this._binding?.root)

        this.viewModel = ViewModelProvider(this)[DetailMovieViewModel::class.java]
        this.viewModel.onLoadDetailMovie(movieId)

        this._binding?.apply {
            viewModel = this@DetailMovieActivity.viewModel
            lifecycleOwner = this@DetailMovieActivity
            rcVideo.adapter = this@DetailMovieActivity.adapter
        }

        this.viewModel.videos.observe(this) {
            this.adapter.onLoad(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
