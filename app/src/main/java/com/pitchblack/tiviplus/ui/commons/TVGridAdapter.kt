package com.pitchblack.tiviplus.ui.commons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pitchblack.tiviplus.R
import com.pitchblack.tiviplus.utils.DataUtils.getYear
import com.pitchblack.tiviplus.data.model.TV
import com.pitchblack.tiviplus.utils.NetworkUtils
import com.pitchblack.tiviplus.databinding.ItemGridMovieTvBinding
import com.pitchblack.tiviplus.ui.tv.main.TVEntityDiffCallback

class TVGridAdapter: ListAdapter<TV, TVGridAdapter.ViewHolder>(TVEntityDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder private constructor(val binding: ItemGridMovieTvBinding): RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemGridMovieTvBinding.inflate(inflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(item: TV) {
            Glide.with(binding.root.context)
                .load(NetworkUtils.getPosterPath(item.posterPath))
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.animation_loading)
                        .error(R.drawable.ic_baseline_broken_image_24))
                .into(binding.imgPoster)
            binding.txtTitle.text = item.name
            binding.txtYear.text = item.firstAirDate.getYear()
        }
    }
}