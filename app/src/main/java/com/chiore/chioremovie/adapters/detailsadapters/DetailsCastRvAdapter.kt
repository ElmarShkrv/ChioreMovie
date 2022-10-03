package com.chiore.chioremovie.adapters.detailsadapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chiore.chioremovie.data.model.movies.Cast
import com.chiore.chioremovie.databinding.CastsItemBinding
import com.chiore.chioremovie.util.Constants.Companion.BASE_IMAGE_URL

class DetailsCastRvAdapter() :
    ListAdapter<Cast, DetailsCastRvAdapter.DetailsCastViewHolder>(DiffUtilCallBack()) {



    class DetailsCastViewHolder(val binding: CastsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Cast) {
            with(binding) {
            Glide.with(root).load(BASE_IMAGE_URL + data.profile_path)
                    .into(castActorIv)

                castActorNameTv.text = data.name
                castCharacterNameTv.text = data.character
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsCastViewHolder {
        return DetailsCastViewHolder(
            CastsItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: DetailsCastViewHolder, position: Int) {
        val result = getItem(position)
        holder.bind(result)

    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Cast>() {
        override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
            return oldItem == newItem
        }

    }
}