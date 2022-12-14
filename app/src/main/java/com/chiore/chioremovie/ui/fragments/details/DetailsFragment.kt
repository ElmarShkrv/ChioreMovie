package com.chiore.chioremovie.ui.fragments.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.chiore.chioremovie.R
import com.chiore.chioremovie.adapters.detailsadapters.*
import com.chiore.chioremovie.data.model.movies.MovieDetailResponse
import com.chiore.chioremovie.databinding.FragmentDetailsBinding
import com.chiore.chioremovie.util.Constants.Companion.BASE_IMAGE_URL
import com.chiore.chioremovie.util.DefaultItemDecorator
import com.chiore.chioremovie.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<DetailsFragmentArgs>()
    private val viewModel by viewModels<DetailsViewModel>()

    private lateinit var detailsGenreRvAdapter: DetailsGenreRvAdapter
    private lateinit var detailsTrailerRvAdapter: DetailsTrailerRvAdapter
    private lateinit var detailsCastRvAdapter: DetailsCastRvAdapter
    private lateinit var detailsSimilarRvAdapter: DetailsSimilarRvAdapter
    private lateinit var detailsRecommendationRvAdapter: DetailsRecommendationRvAdapter

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

        viewModel.detailReview(args.detailId)
        observeReviewResponse()

        viewModel.detailCasts(args.detailId)
        observeCastsResponse()
        setupCastsRv()

        viewModel.detailSimilarMovie(args.detailId)
        observeSimilarResponse()
        setupSimilarRv()

        viewModel.detailsRecommendationMovies(args.detailId)
        observeRecommendationResponse()
        setupRecommendationRv()

        binding.detailsBackIv.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.detailsSeeAllReviews.setOnClickListener { view ->
            val action = DetailsFragmentDirections
                .actionDetailsFragmentToReviewFragment(args.detailId)
            Navigation.findNavController(view).navigate(action)
        }

    }

    private fun setupRecommendationRv() {
        detailsRecommendationRvAdapter = DetailsRecommendationRvAdapter()
        binding.recommendationRv.adapter = detailsRecommendationRvAdapter
    }

    private fun observeRecommendationResponse() {
        lifecycleScope.launch {
            viewModel.detailsRecommendation.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is Resource.Loading -> {
                        response.message?.let { message ->
                            Log.e(TAG, "An error occured: $message")
                        }
                    }
                    is Resource.Success -> {
                        response.data?.let { detailsRecommendationsResponse ->
                            detailsRecommendationRvAdapter.submitList(detailsRecommendationsResponse)
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

    private fun setupSimilarRv() {
        detailsSimilarRvAdapter = DetailsSimilarRvAdapter()
        binding.similarRv.adapter = detailsSimilarRvAdapter
    }

    private fun observeSimilarResponse() {
        lifecycleScope.launch {
            viewModel.detailsSimilar.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is Resource.Loading -> {
                        response.message?.let { message ->
                            Log.e(TAG, "An error occured: $message")
                        }
                    }
                    is Resource.Success -> {
                        response.data?.let { detailsSimilarResponse ->
                            detailsSimilarRvAdapter.submitList(detailsSimilarResponse)
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

    private fun setupCastsRv() {
        detailsCastRvAdapter = DetailsCastRvAdapter()
        binding.castsRv.adapter = detailsCastRvAdapter
        binding.castsRv.addItemDecoration(DefaultItemDecorator(
            resources.getDimensionPixelSize(R.dimen.horizontal_margin_for_horizontal),
            resources.getDimensionPixelSize(R.dimen.vertical_margin_for_horizontal)
        ))
    }

    private fun observeCastsResponse() {
        lifecycleScope.launch {
            viewModel.detailsCasts.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is Resource.Loading -> {
                        response.message?.let { message ->
                            Log.e(TAG, "An error occured: $message")
                        }
                    }
                    is Resource.Success -> {
                        response.data?.let { detailsCastsResponse ->
                            detailsCastRvAdapter.submitList(detailsCastsResponse.cast)
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

    private fun observeReviewResponse() {
        lifecycleScope.launch {
            viewModel.detailsReview.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is Resource.Loading -> {
                        response.message?.let { message ->
                            Log.e(TAG, "An error occured: $message")
                        }
                    }
                    is Resource.Success -> {
                        response.data?.let { reviewResponse ->
                            binding.apply {
                                reviewResponse.results.map { review ->

                                    Glide.with(root)
                                        .load(BASE_IMAGE_URL + review.author_details.avatar_path)
                                        .into(reviewsIv)

                                    detailsReviewsNameTv.text = review.author
                                    detailsReviewsDateTv.text = review.updated_at

                                    detailsReviewTv.text = review.content

                                    var isTextViewClicked = false

                                    detailsReviewTv.setOnClickListener {
                                        if (isTextViewClicked) {
                                            detailsReviewTv.maxLines = 5
                                            isTextViewClicked = false
                                        } else {
                                            detailsReviewTv.maxLines = review.content.length
                                            isTextViewClicked = true
                                        }
                                    }

                                    detailsReviewRateTv.text =
                                        review.author_details.rating.toString()
                                }
                            }
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

                            binding.detailsSaveBtn.setOnClickListener {
                                viewModel.saveMovie(detailsResponse)
                                Toast.makeText(
                                    requireContext(),
                                    "Movie is saved",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

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