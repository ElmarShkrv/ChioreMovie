package com.chiore.chioremovie.ui.fragments.seeallfragments.nowplaying

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.ui.setupWithNavController
import com.chiore.chioremovie.R
import com.chiore.chioremovie.adapters.seealladapters.SeeAllNowPlayingRvAdapter
import com.chiore.chioremovie.databinding.FragmentNowPlayingBinding
import com.chiore.chioremovie.util.DefaultItemDecorator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NowPlayingFragment : Fragment() {

    private var _binding: FragmentNowPlayingBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<NowPlayingViewModel>()

    private lateinit var seeAllNowPlayingRvAdapter: SeeAllNowPlayingRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentNowPlayingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nowPlayingToolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        viewModel.allNowPlayingMovies.observe(viewLifecycleOwner) { allNowPlayingResponse ->
            seeAllNowPlayingRvAdapter.submitData(viewLifecycleOwner.lifecycle,
                allNowPlayingResponse)
        }

        setupNowPlayingRv()

    }

    private fun setupNowPlayingRv() {
        seeAllNowPlayingRvAdapter = SeeAllNowPlayingRvAdapter()
        binding.apply {
            seeAllNowPlayingRv.adapter = seeAllNowPlayingRvAdapter
            seeAllNowPlayingRv.addItemDecoration(DefaultItemDecorator(
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