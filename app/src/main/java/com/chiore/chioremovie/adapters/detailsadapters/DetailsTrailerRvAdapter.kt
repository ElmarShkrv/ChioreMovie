package com.chiore.chioremovie.adapters.detailsadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chiore.chioremovie.databinding.TrailerItemBinding
import com.chiore.chioremovie.util.Constants.Companion.BASE_THUMBNAIL_URL_1
import com.chiore.chioremovie.util.Constants.Companion.BASE_THUMBNAIL_URL_2
import com.chiore.chioremovie.data.model.movies.Result

class DetailsTrailerRvAdapter() :
    ListAdapter<Result, DetailsTrailerRvAdapter.DetailsTrailerViewHolder>(DiffUtilCallBack()) {

    class DetailsTrailerViewHolder(val binding: TrailerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(resultDto: Result) {
            with(binding) {
            Glide.with(root).load(BASE_THUMBNAIL_URL_1 + resultDto.key + BASE_THUMBNAIL_URL_2)
                    .into(trailerThumbnailIv)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsTrailerViewHolder {
        return DetailsTrailerViewHolder(
            TrailerItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: DetailsTrailerViewHolder, position: Int) {
        val result = getItem(position)
        holder.bind(result)
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

    }
}