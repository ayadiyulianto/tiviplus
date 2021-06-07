package com.pitchblack.tiviplus.ui.commons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pitchblack.tiviplus.R
import com.pitchblack.tiviplus.data.model.Image
import com.pitchblack.tiviplus.databinding.ItemImagePortraitBinding
import com.pitchblack.tiviplus.utils.NetworkUtils

class ImagesAdapter: ListAdapter<Image, ImagesAdapter.ViewHolder>(ImageEntityDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder private constructor(val binding: ItemImagePortraitBinding): RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemImagePortraitBinding.inflate(inflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(image: Image) {
            Glide.with(binding.root.context)
                .load(NetworkUtils.getProfilePath(image.filePath))
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.animation_loading)
                        .error(R.drawable.ic_baseline_broken_image_24))
                .into(binding.imgView)
        }
    }
}

class ImageEntityDiffCallback: DiffUtil.ItemCallback<Image>() {
    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem.filePath == newItem.filePath
    }

    override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem == newItem
    }
}