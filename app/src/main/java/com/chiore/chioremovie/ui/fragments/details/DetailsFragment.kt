package com.chiore.chioremovie.ui.fragments.details

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.chiore.chioremovie.R
import com.chiore.chioremovie.adapters.detailsadapters.DetailsGenreRvAdapter
import com.chiore.chioremovie.adapters.detailsadapters.DetailsTrailerRvAdapter
import com.chiore.chioremovie.data.model.movies.Genre
import com.chiore.chioremovie.data.model.movies.MovieDetailResponse
import com.chiore.chioremovie.databinding.FragmentDetailsBinding
import com.chiore.chioremovie.util.Constants.Companion.BASE_IMAGE_URL
import com.chiore.chioremovie.util.DefaultItemDecorator
import com.chiore.chioremovie.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<DetailsFragmentArgs>()
    private val viewModel by viewModels<DetailsViewModel>()

    private lateinit var detailsGenreRvAdapter: DetailsGenreRvAdapter
    private lateinit var detailsTrailerRvAdapter: DetailsTrailerRvAdapter

    val TAG = "DetailsFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.movieDetails(args.detailId)
        observeDetailsResponse()
        setupGenreRv()

        viewModel.movieVideo(args.detailId)
        observeTrailerVideosResponse()
        setupTrailerRv()

    }

    private fun setupTrailerRv() {
        detailsTrailerRvAdapter = DetailsTrailerRvAdapter()
        binding.trailerRv.adapter = detailsTrailerRvAdapter
        binding.trailerRv.addItemDecoration(DefaultItemDecorator(
            resources.getDimensionPixelSize(R.dimen.horizontal_margin_for_horizontal),
            resources.getDimensionPixelSize(R.dimen.vertical_margin_for_horizontal)
        ))
    }

    private fun observeTrailerVideosResponse() {
        lifecycleScope.launch {
            viewModel.detailsMovieVideos.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is Resource.Loading -> {
                        response.message?.let { message ->
                            Log.e(TAG, "An error occured: $message")
                        }
                    }
                    is Resource.Success -> {
                        response.data?.let { detailsTrailerResponse ->
                            detailsTrailerRvAdapter.submitList(detailsTrailerResponse)
                        }
                    }
                    is Resource.Error -> {
                        response.message?.let { message ->
                            Log.e(TAG, "An error occured: $message")
                        }
                    }
                }
            }
        }
    }

    private fun setupGenreRv() {
        detailsGenreRvAdapter = DetailsGenreRvAdapter()
        binding.detailsGemreRv.adapter = detailsGenreRvAdapter
    }

    private fun observeDetailsResponse() {
        lifecycleScope.launch {
            viewModel.detailsMovie.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is Resource.Loading -> {
                        response.message?.let { message ->
                            Log.e(TAG, "An error occured: $message")
                        }
                    }
                    is Resource.Success -> {
                        response.data?.let { detailsResponse ->
                            setupUi(detailsResponse)
                            detailsGenreRvAdapter.submitList(detailsResponse.genres)
                        }
                    }
                    is Resource.Error -> {
                        response.message?.let { message ->
                            Log.e(TAG, "An error occured: $message")
                        }
                    }
                }
            }
        }
    }

    private fun setupUi(data: MovieDetailResponse) {
        binding.apply {

            Glide.with(root).load(BASE_IMAGE_URL + data.poster_path).into(detailsIv)

            detailsNameTv.text = data.original_title

            val movieRating = data.vote_average / 2

            ratingBar.rating = movieRating.toFloat()

            detailsRateTv.text = "Total Vote:  ${data.vote_count}"

            detailsDateTv.text = data.release_date

            detailsBudgetTv.text = "Budget:  ${data.budget}"

            detailsOverviewTv.text = data.overview

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}