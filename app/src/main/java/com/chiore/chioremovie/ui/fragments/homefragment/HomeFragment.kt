package com.chiore.chioremovie.ui.fragments.homefragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.chiore.chioremovie.R
import com.chiore.chioremovie.adapters.homeadapters.HomeNowPlayingRvAdapter
import com.chiore.chioremovie.adapters.homeadapters.HomePopularRvAdapter
import com.chiore.chioremovie.adapters.homeadapters.HomeTopRatedRvAdapter
import com.chiore.chioremovie.adapters.homeadapters.HomeUpcomingRvAdapter
import com.chiore.chioremovie.databinding.FragmentHomeBinding
import com.chiore.chioremovie.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    private lateinit var homeNowPlayingRvAdapter: HomeNowPlayingRvAdapter
    private lateinit var homePopularRvAdapter: HomePopularRvAdapter
    private lateinit var homeUpcomingRvAdapter: HomeUpcomingRvAdapter
    private lateinit var homeTopRatedRvAdapter: HomeTopRatedRvAdapter

    val TAG = "HomeFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeNowPlayingRvAdapter = HomeNowPlayingRvAdapter()
        homePopularRvAdapter = HomePopularRvAdapter()
        homeUpcomingRvAdapter = HomeUpcomingRvAdapter()
        homeTopRatedRvAdapter = HomeTopRatedRvAdapter()

        viewModel.nowPlayingMovies()
        observeNowPlayingMovie()
        setupNowPlayingRv()

        viewModel.popularMovies()
        observePopularMovie()
        setupPopularRv()

        viewModel.upcomingMovies()
        observeUpcomingMovie()
        setupUpcomingRv()

        viewModel.topRatedMovies()
        observeTopRatedMovie()
        setupTopRatedRv()

        binding.seeAllNowPlaying.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_nowPlayingFragment)
        }
    }

    private fun setupTopRatedRv() {
        binding.topRatedRv.adapter = homeTopRatedRvAdapter
    }

    private fun observeTopRatedMovie() {
        lifecycleScope.launch {
            viewModel.topRatedMovie.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is Resource.Loading -> {
                        response.message?.let { message ->
                            Log.e(TAG, "An error occured: $message")
                        }
                    }
                    is Resource.Success -> {
                        response.data?.let { topRatedResponse ->
                            homeTopRatedRvAdapter.submitList(topRatedResponse)
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

    private fun setupUpcomingRv() {
        binding.upcomingRv.adapter = homeUpcomingRvAdapter
    }

    private fun observeUpcomingMovie() {
        lifecycleScope.launch {
            viewModel.upcomingMovie.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is Resource.Loading -> {
                        response.message?.let { message ->
                            Log.e(TAG, "An error occured: $message")
                        }
                    }
                    is Resource.Success -> {
                        response.data?.let { upcomingResponse ->
                            homeUpcomingRvAdapter.submitList(upcomingResponse)
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

    private fun setupPopularRv() {
        binding.popularRv.adapter = homePopularRvAdapter
    }

    private fun observePopularMovie() {
        lifecycleScope.launch {
            viewModel.popularMovie.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is Resource.Loading -> {
                        response.message?.let { message ->
                            Log.e(TAG, "An error occured: $message")
                        }
                    }
                    is Resource.Success -> {
                        response.data?.let { popolarResponse ->
                            homePopularRvAdapter.submitList(popolarResponse)
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

    private fun setupNowPlayingRv() {
        binding.nowPlayingRv.adapter = homeNowPlayingRvAdapter
    }

    private fun observeNowPlayingMovie() {
        lifecycleScope.launch {
            viewModel.nowPlayingMovie.observe(viewLifecycleOwner) { response ->

                when (response) {
                    is Resource.Loading -> {
                        response.message?.let { message ->
                            Log.e(TAG, "An error occured: $message")
                        }
                    }
                    is Resource.Success -> {
                        response.data?.let { nowPlayingResponse ->
                            homeNowPlayingRvAdapter.submitList(nowPlayingResponse)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}