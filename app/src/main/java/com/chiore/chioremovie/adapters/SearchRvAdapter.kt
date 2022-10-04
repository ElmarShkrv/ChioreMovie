package com.chiore.chioremovie.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chiore.chioremovie.data.model.movies.ResultDto
import com.chiore.chioremovie.databinding.SeeAllItemBinding
import com.chiore.chioremovie.ui.fragments.search.SearchFragmentDirections
import com.chiore.chioremovie.ui.fragments.seeallfragments.nowplaying.NowPlayingFragmentDirections
import com.chiore.chioremovie.util.Constants.Companion.BASE_IMAGE_URL

class SearchRvAdapter() :
    ListAdapter<ResultDto, SearchRvAdapter.SearchViewHolder>(DiffUtilCallBack()) {

    class SearchViewHolder(val binding: SeeAllItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(resultDto: ResultDto) {
            with(binding) {
                Glide.with(root).load(BASE_IMAGE_URL + resultDto.poster_path).into(seeAllIv)

                seeAllItemNameTv.text = resultDto.original_title
                seeAllItemDateTv.text = resultDto.release_date
                seeAllItemRateTv.text = "${resultDto.vote_average}"

                itemView.setOnClickListener { view ->
                    val action = SearchFragmentDirections
                        .actionSearchFragmentToDetailsFragment(resultDto.id)
                    Navigation.findNavController(view).navigate(action)
                }

            }
        }
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val result = getItem(position)
        if (result != null) {
            holder.bind(result)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
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