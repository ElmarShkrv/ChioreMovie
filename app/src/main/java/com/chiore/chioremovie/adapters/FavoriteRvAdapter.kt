package com.chiore.chioremovie.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chiore.chioremovie.data.model.movies.MovieDetailResponse
import com.chiore.chioremovie.databinding.SeeAllItemBinding
import com.chiore.chioremovie.ui.fragments.favorite.FavoriteFragmentDirections
import com.chiore.chioremovie.util.Constants.Companion.BASE_IMAGE_URL

class FavoriteRvAdapter() :
    ListAdapter<MovieDetailResponse, FavoriteRvAdapter.FavoriteViewHolder>(
        DiffUtilCallBack()) {

    class FavoriteViewHolder(val binding: SeeAllItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: MovieDetailResponse) {
            with(binding) {
                Glide.with(root).load(BASE_IMAGE_URL + result.poster_path).into(seeAllIv)

                seeAllItemNameTv.text = result.original_title
                seeAllItemDateTv.text = result.release_date
                seeAllItemRateTv.text = "${result.vote_average}"

                itemView.setOnClickListener { view ->
                    val action = FavoriteFragmentDirections
                        .actionFavoriteFragmentToDetailsFragment(result.id)
                    Navigation.findNavController(view).navigate(action)
                }

            }
        }
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val result = getItem(position)
        if (result != null) {
            holder.bind(result)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            SeeAllItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<MovieDetailResponse>() {
        override fun areItemsTheSame(
            oldItem: MovieDetailResponse,
            newItem: MovieDetailResponse,
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MovieDetailResponse,
            newItem: MovieDetailResponse,
        ): Boolean {
            return oldItem == newItem
        }

    }
}