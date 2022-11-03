package com.appinc.movieapp.views.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.appinc.movieapp.R
import com.appinc.movieapp.databinding.ActivityMainBinding
import com.appinc.movieapp.views.detailMovie.DetailMovieActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this._binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this._binding?.root)

        this.viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        this._binding?.apply {
            this.viewModel = this@MainActivity.viewModel
            this.lifecycleOwner = this@MainActivity
            this.adapter = MainAdapter(this@MainActivity.viewModel)
        }

        this.viewModel.movieId.observe(this) {
            startActivity(
                Intent(this, DetailMovieActivity::class.java).apply {
                    putExtra("movieId", it)
                }
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_filter, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        item.isChecked = true
        when (item.itemId) {
            R.id.op_favorite -> {
                viewModel.onFilterFavorite()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
