package com.chiore.chioremovie.ui.fragments.seeallfragments.review

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.chiore.chioremovie.R
import com.chiore.chioremovie.adapters.seealladapters.SeeAllReviewRvAdapter
import com.chiore.chioremovie.databinding.FragmentReviewBinding
import com.chiore.chioremovie.util.DefaultItemDecorator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewFragment : Fragment() {

    private var _binding: FragmentReviewBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<ReviewFragmentArgs>()
    private val viewModel by viewModels<ReviewViewModel>()

    private lateinit var seeAllReviewRvAdapter: SeeAllReviewRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentReviewBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.allReview(args.movieId)
        viewModel.seeAllReviews.value?.observe(viewLifecycleOwner) { allReviews ->
            seeAllReviewRvAdapter.submitData(viewLifecycleOwner.lifecycle, allReviews)
        }

        setupReviewRv()
    }

    private fun setupReviewRv() {
        seeAllReviewRvAdapter = SeeAllReviewRvAdapter()
        binding.apply {
            reviewRv.adapter = seeAllReviewRvAdapter
            reviewRv.addItemDecoration(DefaultItemDecorator(
                resources.getDimensionPixelSize(R.dimen.horizontal_margin_for_vertical),
                resources.getDimensionPixelSize(R.dimen.vertical_margin_for_vertical)
            ))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}