package com.pitchblack.tiviplus.ui.movie.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.pitchblack.tiviplus.R
import com.pitchblack.tiviplus.Utils.getYear
import com.pitchblack.tiviplus.data.model.Movie
import com.pitchblack.tiviplus.data.network.RestAPI
import com.pitchblack.tiviplus.databinding.ItemMovieTvBinding

class MovieListAdapter(private val clickListener: MovieClickListener)
    : ListAdapter<Movie, MovieListAdapter.ViewHolder>(MovieEntityDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListAdapter.ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MovieListAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class ViewHolder private constructor(val binding: ItemMovieTvBinding)
        : RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemMovieTvBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(item: Movie, clickListener: MovieClickListener) {
            Glide.with(binding.root.context)
                .load(RestAPI.getPosterPath(item.posterPath))
                .apply(RequestOptions()
                    .placeholder(R.drawable.animation_loading)
                    .error(R.drawable.ic_baseline_broken_image_24))
                .into(binding.imgItemMainMovieTv)
            binding.txtTitleItemMainMovieTv.text = item.title
            binding.txtSubtitleItemMainMovieTv.text = item.releaseDate.getYear()
            binding.txtDescItemMainMovieTv.text = item.overview
            binding.txtRatingItemMainMovieTv.text = item.voteAverage.toString()
            binding.cvMovieTv.setOnClickListener {
                clickListener.onClick(item)
            }
        }
    }
}

class MovieEntityDiffCallback : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

}

class MovieClickListener(val clickListener: (id: Int) -> Unit) {
    fun onClick(movie: Movie) = clickListener(movie.id)
}