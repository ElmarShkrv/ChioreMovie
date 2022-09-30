package com.chiore.chioremovie.ui.fragments.seeallfragments.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.chiore.chioremovie.R
import com.chiore.chioremovie.adapters.seealladapters.SeeAllNowPlayingRvAdapter
import com.chiore.chioremovie.adapters.seealladapters.SeeAllPopularRvAdapter
import com.chiore.chioremovie.databinding.FragmentPopularBinding
import com.chiore.chioremovie.util.DefaultItemDecorator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularFragment : Fragment() {

    private var _binding: FragmentPopularBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<PopularViewModel>()

    private lateinit var seeAllPopularRvAdapter: SeeAllPopularRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentPopularBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.popularToolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        viewModel.allPopularMovies.observe(viewLifecycleOwner) { allPopularResponse ->
            seeAllPopularRvAdapter.submitData(viewLifecycleOwner.lifecycle, allPopularResponse)
        }

        setupPopularRv()

    }

    private fun setupPopularRv() {
        seeAllPopularRvAdapter = SeeAllPopularRvAdapter()
        binding.apply {
            seeAllPopularRv.adapter = seeAllPopularRvAdapter
            seeAllPopularRv.addItemDecoration(DefaultItemDecorator(
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