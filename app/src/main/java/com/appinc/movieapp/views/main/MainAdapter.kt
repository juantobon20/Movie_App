package com.appinc.movieapp.views.main

import com.appinc.movieapp.R
import com.appinc.movieapp.data.model.Movie
import com.appinc.movieapp.databinding.ItemCardMovieBinding
import com.appinc.movieapp.util.BaseAdapter
import com.appinc.movieapp.util.IOnClickListener

class MainAdapter(private val iOnClickListener: IOnClickListener<Movie>) :
    BaseAdapter<ItemCardMovieBinding, Movie>() {
    override val layoutId: Int get() = R.layout.item_card_movie

    override fun bind(binding: ItemCardMovieBinding, item: Movie) {
        binding.apply {
            listener = iOnClickListener; movie = item; executePendingBindings()
        }
    }
}
