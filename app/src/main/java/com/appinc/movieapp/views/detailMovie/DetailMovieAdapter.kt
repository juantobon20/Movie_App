package com.appinc.movieapp.views.detailMovie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appinc.movieapp.R
import com.appinc.movieapp.data.model.Video
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class DetailMovieAdapter : RecyclerView.Adapter<DetailMovieAdapter.ViewHolder>() {

    private var videos: List<Video> = arrayListOf()

    fun onLoad(video: List<Video>) {
        this.videos = video; notifyItemRangeInserted(0, videos.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_card_preview_youtube, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val video = this.videos[position]
        holder.youtubePlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                youTubePlayer.apply {
                    cueVideo(video.key, 0F)
                }
            }
        })
    }

    override fun getItemCount(): Int = videos.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val youtubePlayer: YouTubePlayerView = view.findViewById(R.id.youtubePlayer)
    }
}