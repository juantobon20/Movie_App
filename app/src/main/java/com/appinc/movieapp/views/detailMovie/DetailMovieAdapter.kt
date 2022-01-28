package com.appinc.movieapp.views.detailMovie

import com.appinc.movieapp.R
import com.appinc.movieapp.data.model.Video
import com.appinc.movieapp.databinding.ItemCardPreviewYoutubeBinding
import com.appinc.movieapp.util.BaseAdapter

class DetailMovieAdapter : BaseAdapter<ItemCardPreviewYoutubeBinding, Video>() {
    override val layoutId: Int get() = R.layout.item_card_preview_youtube

    override fun bind(binding: ItemCardPreviewYoutubeBinding, item: Video) {
        binding.apply {
            video = item; executePendingBindings()
        }
    }
}