package com.pitchblack.tiviplus.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pitchblack.tiviplus.R
import com.pitchblack.tiviplus.data.model.Video
import com.pitchblack.tiviplus.data.network.RestAPI
import com.pitchblack.tiviplus.databinding.ItemDetailVideosBinding

class VideoListAdapter: ListAdapter<Video, VideoListAdapter.ViewHolder>(VideoEntityDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder private constructor(val binding: ItemDetailVideosBinding): RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemDetailVideosBinding.inflate(inflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(item: Video) {
            Glide.with(binding.root.context)
                .load(RestAPI.getYoutubePlaceholder(item.key))
                .apply(
                    RequestOptions()
                    .placeholder(R.drawable.animation_loading)
                    .error(R.drawable.ic_baseline_broken_image_24))
                .into(binding.imgDetailVideoThumbnail)
        }
    }
}

class VideoEntityDiffCallback: DiffUtil.ItemCallback<Video>() {
    override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {
        return oldItem == newItem
    }
}