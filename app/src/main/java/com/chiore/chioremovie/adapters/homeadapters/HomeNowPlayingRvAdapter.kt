package com.chiore.chioremovie.adapters.homeadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chiore.chioremovie.data.model.movies.ResultDto
import com.chiore.chioremovie.databinding.NowPlayingItemBinding
import com.chiore.chioremovie.ui.fragments.homefragment.HomeFragmentDirections
import com.chiore.chioremovie.util.Constants.Companion.BASE_IMAGE_URL

class HomeNowPlayingRvAdapter() :
    ListAdapter<ResultDto, HomeNowPlayingRvAdapter.HomeNowPlayingViewHolder>(DiffUtilCallBack()) {

    class HomeNowPlayingViewHolder(val binding: NowPlayingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(resultDto: ResultDto) {
            with(binding) {
                Glide.with(root).load(BASE_IMAGE_URL + resultDto.poster_path).into(backImage)
                Glide.with(root).load(BASE_IMAGE_URL + resultDto.backdrop_path).into(nowPlayingIv)

                nowPlayingNameTv.text = resultDto.original_title

                nowPlayingRateTv.text = "${resultDto.vote_average}"

                itemView.setOnClickListener { view ->
                    val action = HomeFragmentDirections
                        .actionHomeFragmentToDetailsFragment(resultDto.id)
                    Navigation.findNavController(view).navigate(action)
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeNowPlayingViewHolder {
        return HomeNowPlayingViewHolder(
            NowPlayingItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: HomeNowPlayingViewHolder, position: Int) {
        val result = getItem(position)
        holder.bind(result)
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