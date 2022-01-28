package com.appinc.movieapp.util

import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.appinc.movieapp.R
import com.appinc.movieapp.URL_IMAGE
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@BindingAdapter("onLoadUrl")
fun ImageView.onLoadUrl(url: String?) {
    if (url.isNullOrEmpty()) return

    val circular = CircularProgressDrawable(context).apply {
        strokeWidth = 5F; centerRadius = 30F; start()
    }
    Glide.with(this)
        .load(URL_IMAGE + url)
        .placeholder(circular).into(this)
}

@BindingAdapter("setAdapter")
fun setAdapter(
    recyclerView: RecyclerView,
    adapter: BaseAdapter<*, *>?
) {
    adapter?.let {
        recyclerView.adapter = it
    }
}

@BindingAdapter("isVisibleFromList")
fun View.isVisible(list: List<Nothing>?) {
    this.isVisible = !list.isNullOrEmpty()
}

@BindingAdapter("isFavorite")
fun FloatingActionButton.isFavorite(isFavorite: Boolean){
    this.setImageResource(if (isFavorite) R.drawable.heart_2 else R.drawable.heart)
}

@BindingAdapter("submitList")
fun submitList(recyclerView: RecyclerView, list: List<Nothing>?) {
    if (list.isNullOrEmpty()) return

    val adapter = recyclerView.adapter as BaseAdapter<*, *>?
    adapter?.loadData(list)
}

@BindingAdapter("setVideoId")
fun YouTubePlayerView.setVideoId(videoId: String){
    this.setVideoId(videoId)
}