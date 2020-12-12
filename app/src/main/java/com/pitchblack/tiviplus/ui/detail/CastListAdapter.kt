package com.pitchblack.tiviplus.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pitchblack.tiviplus.R
import com.pitchblack.tiviplus.data.model.Cast
import com.pitchblack.tiviplus.data.network.RestAPI
import com.pitchblack.tiviplus.databinding.ItemDetailCastBinding

class CastsListAdapter: ListAdapter<Cast, CastsListAdapter.ViewHolder>(CastEntityDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder private constructor(val binding: ItemDetailCastBinding): RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemDetailCastBinding.inflate(inflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(item: Cast) {
            Glide.with(binding.root.context)
                .load(RestAPI.getProfilePath(item.profilePath))
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.animation_loading)
                        .error(R.drawable.ic_baseline_broken_image_24))
                .into(binding.imgPeople)
            binding.txtPeopleName.text = item.name
            binding.txtPeopleRole.text = item.character
        }
    }
}

class CastEntityDiffCallback: DiffUtil.ItemCallback<Cast>() {
    override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
        return oldItem == newItem
    }
}