package com.pitchblack.tiviplus.ui.celebs.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pitchblack.tiviplus.R
import com.pitchblack.tiviplus.data.model.Celebs
import com.pitchblack.tiviplus.utils.NetworkUtils
import com.pitchblack.tiviplus.databinding.ItemPeopleBinding

class PeopleAdapter(private val clickListener: PeopleClickListener)
    : ListAdapter<Celebs, PeopleAdapter.ViewHolder>(PeopleEntityDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class ViewHolder private constructor(val binding: ItemPeopleBinding): RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemPeopleBinding.inflate(inflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(item: Celebs, clickListener: PeopleClickListener) {
            Glide.with(binding.root.context)
                .load(NetworkUtils.getProfilePath(item.profilePath))
                .apply(RequestOptions()
                    .placeholder(R.drawable.animation_loading)
                    .error(R.drawable.ic_baseline_broken_image_24))
                .into(binding.imgPeople)
            binding.txtPeopleName.text = item.name
            binding.txtPeopleDepartment.text = item.department
            binding.txtPeopleKnownfor.text = binding.root.context
                .getString(R.string.known_for, item.knownFor)
            binding.cvPeople.setOnClickListener {
                clickListener.onClick(item)
            }
        }
    }
}

class PeopleEntityDiffCallback: DiffUtil.ItemCallback<Celebs>() {
    override fun areItemsTheSame(oldItem: Celebs, newItem: Celebs): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Celebs, newItem: Celebs): Boolean {
        return oldItem == newItem
    }
}

class PeopleClickListener(val clickListener: (id: Int) -> Unit) {
    fun onClick(celebs: Celebs) = clickListener(celebs.id)
}