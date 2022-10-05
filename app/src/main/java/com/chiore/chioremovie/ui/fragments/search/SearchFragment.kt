package com.chiore.chioremovie.ui.fragments.search

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.chiore.chioremovie.R
import com.chiore.chioremovie.adapters.SearchRvAdapter
import com.chiore.chioremovie.databinding.FragmentSearchBinding
import com.chiore.chioremovie.util.DefaultItemDecorator
import com.chiore.chioremovie.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<SearchViewModel>()

    private lateinit var searchRvAdapter: SearchRvAdapter

    val TAG = "SearchFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchRv.visibility = View.GONE

        observeSearchResponse()
        setupSearchRv()

        (activity as AppCompatActivity).setSupportActionBar(binding.searchToolbar)

        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(
            object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(R.menu.search_menu, menu)

                    val search = menu.findItem(R.id.search)
                    val searchView = search?.actionView as SearchView
                    searchView.queryHint = "Search Movies"

                    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            if (query != null) {
                                binding.searchRv.scrollToPosition(0)
                                viewModel.searchMovie(query)
                                binding.searchRv.visibility = View.VISIBLE
                                binding.searchLtAnimation.visibility = View.GONE
                                searchView.clearFocus()
                            }
                            return true
                        }

                        override fun onQueryTextChange(newText: String?): Boolean {
                            return true
                        }
                    })

                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    return true
                }
            }, viewLifecycleOwner, Lifecycle.State.RESUMED
        )

    }

    private fun setupSearchRv() {
        searchRvAdapter = SearchRvAdapter()
        binding.apply {
            searchRv.adapter = searchRvAdapter
            searchRv.addItemDecoration(DefaultItemDecorator(
                resources.getDimensionPixelSize(R.dimen.horizontal_margin_for_vertical),
                resources.getDimensionPixelSize(R.dimen.vertical_margin_for_vertical)
            ))
        }
    }

    private fun observeSearchResponse() {
        lifecycleScope.launch {
            viewModel.searchMovie.observe(viewLifecycleOwner) { response ->
                when (response) {
                    is Resource.Loading -> {
                        response.message?.let { message ->
                            Log.e(TAG, "An error occured: $message")
                        }
                    }
                    is Resource.Success -> {
                        response.data?.let { searchResponse ->
                            searchRvAdapter.submitList(searchResponse)
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