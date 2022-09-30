package com.chiore.chioremovie.ui.fragments.seeallfragments.toprated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.chiore.chioremovie.R
import com.chiore.chioremovie.adapters.homeadapters.HomeTopRatedRvAdapter
import com.chiore.chioremovie.adapters.seealladapters.SeeAllTopRatedRvAdapter
import com.chiore.chioremovie.databinding.FragmentTopRatedBinding
import com.chiore.chioremovie.util.DefaultItemDecorator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopRatedFragment : Fragment() {

    private var _binding: FragmentTopRatedBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<TopRatedViewModel>()

    private lateinit var seeAllTopRatedRvAdapter: SeeAllTopRatedRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentTopRatedBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topRatedToolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        viewModel.allTopRatedMovies.observe(viewLifecycleOwner) { allTopRatedResponse ->
            seeAllTopRatedRvAdapter.submitData(viewLifecycleOwner.lifecycle, allTopRatedResponse)
        }

        setupTopRatedRv()

    }

    private fun setupTopRatedRv() {
        seeAllTopRatedRvAdapter = SeeAllTopRatedRvAdapter()
        binding.apply {
            seeAllTopRatedRv.adapter = seeAllTopRatedRvAdapter
            seeAllTopRatedRv.addItemDecoration(DefaultItemDecorator(
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