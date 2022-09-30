package com.chiore.chioremovie.adapters.detailsadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chiore.chioremovie.data.model.movies.Genre
import com.chiore.chioremovie.data.model.movies.ResultDto
import com.chiore.chioremovie.databinding.GenreItemBinding
import com.chiore.chioremovie.databinding.NowPlayingItemBinding
import com.chiore.chioremovie.ui.fragments.homefragment.HomeFragmentDirections
import com.chiore.chioremovie.util.Constants.Companion.BASE_IMAGE_URL

class DetailsGenreRvAdapter() :
    ListAdapter<Genre, DetailsGenreRvAdapter.DetailsGenreViewHolder>(DiffUtilCallBack()) {

    class DetailsGenreViewHolder(val binding: GenreItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(genre: Genre) {
            with(binding) {
                genreNameTv.text = genre.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsGenreViewHolder {
        return DetailsGenreViewHolder(
            GenreItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: DetailsGenreViewHolder, position: Int) {
        val result = getItem(position)
        holder.bind(result)
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Genre>() {
        override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
            return oldItem == newItem
        }

    }
}