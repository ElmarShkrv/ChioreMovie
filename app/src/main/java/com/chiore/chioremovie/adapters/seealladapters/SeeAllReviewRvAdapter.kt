package com.chiore.chioremovie.adapters.seealladapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chiore.chioremovie.data.model.movies.ReviewsResult
import com.chiore.chioremovie.databinding.ReviewItemBinding
import com.chiore.chioremovie.util.Constants.Companion.BASE_IMAGE_URL

class SeeAllReviewRvAdapter() :
    PagingDataAdapter<ReviewsResult, SeeAllReviewRvAdapter.SeeAllreviewViewHolder>(
        DiffUtilCallBack()) {

    class SeeAllreviewViewHolder(val binding: ReviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: ReviewsResult) {
            with(binding) {
                Glide.with(root).load(BASE_IMAGE_URL + result.author_details.avatar_path)
                    .into(reviewsIv)

                reviewsNameTv.text = result.author
                reviewsDateTv.text = result.updated_at
                reviewsTv.text = result.content

            }
        }
    }

    override fun onBindViewHolder(holder: SeeAllreviewViewHolder, position: Int) {
        val result = getItem(position)
        if (result != null) {
            holder.bind(result)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeeAllreviewViewHolder {
        return SeeAllreviewViewHolder(
            ReviewItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<ReviewsResult>() {
        override fun areItemsTheSame(oldItem: ReviewsResult, newItem: ReviewsResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ReviewsResult, newItem: ReviewsResult): Boolean {
            return oldItem == newItem
        }

    }
}