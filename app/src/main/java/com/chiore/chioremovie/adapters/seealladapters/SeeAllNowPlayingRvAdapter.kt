package com.chiore.chioremovie.adapters.seealladapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chiore.chioremovie.data.model.movies.ResultDto
import com.chiore.chioremovie.databinding.NowPlayingItemBinding
import com.chiore.chioremovie.databinding.SeeAllItemBinding
import com.chiore.chioremovie.util.Constants.Companion.BASE_IMAGE_URL

class SeeAllNowPlayingRvAdapter() :
    PagingDataAdapter<ResultDto, SeeAllNowPlayingRvAdapter.SeeAllNowPlayingViewHolder>(
        DiffUtilCallBack()) {

    class SeeAllNowPlayingViewHolder(val binding: SeeAllItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(resultDto: ResultDto) {
            with(binding) {
                Glide.with(root).load(BASE_IMAGE_URL + resultDto.poster_path).into(seeAllIv)

                seeAllItemNameTv.text = resultDto.original_title
                seeAllItemDateTv.text = resultDto.release_date
                seeAllItemRateTv.text = "${resultDto.vote_average}"

            }
        }
    }

    override fun onBindViewHolder(holder: SeeAllNowPlayingViewHolder, position: Int) {
        val result = getItem(position)
        if (result != null) {
            holder.bind(result)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeeAllNowPlayingViewHolder {
        return SeeAllNowPlayingViewHolder(
            SeeAllItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<ResultDto>() {
        override fun areItemsTheSame(oldItem: ResultDto, newItem: ResultDto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ResultDto, newItem: ResultDto): Boolean {
            return oldItem == newItem
        }

    }
}