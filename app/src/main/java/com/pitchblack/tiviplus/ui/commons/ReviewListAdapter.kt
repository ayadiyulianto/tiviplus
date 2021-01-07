package com.pitchblack.tiviplus.ui.commons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pitchblack.tiviplus.R
import com.pitchblack.tiviplus.data.model.Review
import com.pitchblack.tiviplus.databinding.ItemDetailReviewBinding

class ReviewListAdapter: ListAdapter<Review, ReviewListAdapter.ViewHolder>(ReviewEntityDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder private constructor(val binding: ItemDetailReviewBinding): RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemDetailReviewBinding.inflate(inflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(item: Review) {
            binding.txtPeopleName.text = item.author
            binding.txtRating.text = binding.root.context.getString(R.string.rating_star, item.rating)
            binding.txtCreatedAt.text = item.createdAt
            binding.txtPeopleContent.text = item.content
        }
    }
}

class ReviewEntityDiffCallback: DiffUtil.ItemCallback<Review>() {
    override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem == newItem
    }
}