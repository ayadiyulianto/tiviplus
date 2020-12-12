package com.pitchblack.tiviplus.ui.tv.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pitchblack.tiviplus.R
import com.pitchblack.tiviplus.data.model.TV
import com.pitchblack.tiviplus.data.network.RestAPI
import com.pitchblack.tiviplus.databinding.ItemMovieTvBinding
import java.text.SimpleDateFormat
import java.util.*

class TVListAdapter: ListAdapter<TV, TVListAdapter.ViewHolder>(TVEntityDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVListAdapter.ViewHolder {
        return  ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TVListAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder private constructor (val binding: ItemMovieTvBinding): RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemMovieTvBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(item: TV){
            Glide.with(binding.root.context)
                .load(RestAPI.getPosterPath(item.posterPath))
                .apply(
                    RequestOptions()
                    .placeholder(R.drawable.animation_loading)
                    .error(R.drawable.ic_baseline_broken_image_24))
                .into(binding.imgItemMainMovieTv)
            binding.txtTitleItemMainMovieTv.text = item.name
            binding.txtSubtitleItemMainMovieTv.text = if (item.firstAirDate.isEmpty()) "-" else item.firstAirDate.take(4)
            binding.txtDescItemMainMovieTv.text = item.overview
            binding.txtRatingItemMainMovieTv.text = item.voteAverage.toString()
        }

    }
}

class  TVEntityDiffCallback: DiffUtil.ItemCallback<TV>() {
    override fun areItemsTheSame(oldItem: TV, newItem: TV): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TV, newItem: TV): Boolean {
        return oldItem == newItem
    }
}