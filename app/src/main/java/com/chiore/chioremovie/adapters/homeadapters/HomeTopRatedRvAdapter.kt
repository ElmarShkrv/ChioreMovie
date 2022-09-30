package com.chiore.chioremovie.adapters.homeadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chiore.chioremovie.data.model.movies.ResultDto
import com.chiore.chioremovie.databinding.HomeHorizontalItemBinding
import com.chiore.chioremovie.ui.fragments.homefragment.HomeFragmentDirections
import com.chiore.chioremovie.util.Constants.Companion.BASE_IMAGE_URL

class HomeTopRatedRvAdapter() :
    ListAdapter<ResultDto, HomeTopRatedRvAdapter.HomeTopRatedViewHolder>(DiffUtilCallBack()) {

    class HomeTopRatedViewHolder(val binding: HomeHorizontalItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(resultDto: ResultDto) {
            with(binding) {
                Glide.with(root).load(BASE_IMAGE_URL + resultDto.poster_path).into(horizontalItemIv)

                horizontalItemNameTv.text = resultDto.original_title
                horizontalItemDateTv.text = resultDto.release_date
                HorizontalItemRateTv.text = "${resultDto.vote_average}"

                itemView.setOnClickListener { view ->
                    val action = HomeFragmentDirections
                        .actionHomeFragmentToDetailsFragment(resultDto.id)
                    Navigation.findNavController(view).navigate(action)
                }

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeTopRatedViewHolder {
        return HomeTopRatedViewHolder(
            HomeHorizontalItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: HomeTopRatedViewHolder, position: Int) {
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