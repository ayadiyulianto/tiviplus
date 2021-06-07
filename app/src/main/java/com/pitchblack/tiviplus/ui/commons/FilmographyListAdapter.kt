package com.pitchblack.tiviplus.ui.commons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pitchblack.tiviplus.R
import com.pitchblack.tiviplus.data.model.Filmography
import com.pitchblack.tiviplus.databinding.ItemGridMovieTvBinding
import com.pitchblack.tiviplus.utils.NetworkUtils

class FilmographyListAdapter: ListAdapter<Filmography, FilmographyListAdapter.ViewHolder>(
    FilmographyEntityDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder private constructor(val binding: ItemGridMovieTvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemGridMovieTvBinding.inflate(inflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(item: Filmography) {
            Glide.with(binding.root.context)
                .load(NetworkUtils.getPosterPath(item.posterPath))
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.animation_loading)
                        .error(R.drawable.ic_baseline_broken_image_24)
                )
                .into(binding.imgPoster)
            binding.txtTitle.text = item.name
            binding.txtYear.text = binding.root.context
                .getString(R.string.character, item.character, item.releaseYear)
        }
    }
}

class FilmographyEntityDiffCallback: DiffUtil.ItemCallback<Filmography>() {
    override fun areItemsTheSame(oldItem: Filmography, newItem: Filmography): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Filmography, newItem: Filmography): Boolean {
        return oldItem == newItem
    }
}