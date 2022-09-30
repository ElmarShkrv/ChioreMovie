package com.chiore.chioremovie.ui.fragments.seeallfragments.upcoming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.chiore.chioremovie.R
import com.chiore.chioremovie.adapters.seealladapters.SeeAllUpcomingRvAdapter
import com.chiore.chioremovie.databinding.FragmentUpcomingBinding
import com.chiore.chioremovie.util.DefaultItemDecorator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpcomingFragment : Fragment() {

    private var _binding: FragmentUpcomingBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<UpcomingViewModel>()

    private lateinit var seeAllUpcomingRvAdapter: SeeAllUpcomingRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentUpcomingBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.upcomingToolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        viewModel.allUpcomingMovies.observe(viewLifecycleOwner) { allUpcomingResponse ->
            seeAllUpcomingRvAdapter.submitData(viewLifecycleOwner.lifecycle, allUpcomingResponse)
        }

        setupUpcomingRv()

    }

    private fun setupUpcomingRv() {
        seeAllUpcomingRvAdapter = SeeAllUpcomingRvAdapter()
        binding.apply {
            seeAllUpcomingRv.adapter = seeAllUpcomingRvAdapter
            seeAllUpcomingRv.addItemDecoration(DefaultItemDecorator(
                resources.getDimensionPixelSize(R.dimen.horizontal_margin),
                resources.getDimensionPixelSize(R.dimen.vertical_margin)
            ))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}