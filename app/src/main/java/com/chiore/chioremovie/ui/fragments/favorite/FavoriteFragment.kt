package com.chiore.chioremovie.ui.fragments.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.chiore.chioremovie.R
import com.chiore.chioremovie.adapters.FavoriteRvAdapter
import com.chiore.chioremovie.databinding.FragmentFavoriteBinding
import com.chiore.chioremovie.util.DefaultItemDecorator
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<FavoriteViewModel>()

    private lateinit var favoriteRvAdapter: FavoriteRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getMovies()
        setupFavoriteRv()

        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ) = true

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.layoutPosition

                val deletedMovie = favoriteRvAdapter.currentList[position]
                val insertMovie = favoriteRvAdapter.currentList[position]

                viewModel.deleteMovie(deletedMovie)

                Snackbar.make(requireView(), "Movie deleted", Snackbar.LENGTH_LONG).setAction(
                    "Undo",
                    View.OnClickListener {
                        viewModel.saveMovie(insertMovie)
                    }
                ).show()
            }
        }
        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding.favoriteMovieRv)

    }

    private fun setupFavoriteRv() {
        favoriteRvAdapter = FavoriteRvAdapter()
        binding.apply {
            favoriteMovieRv.adapter = favoriteRvAdapter
            favoriteMovieRv.addItemDecoration(DefaultItemDecorator(
                resources.getDimensionPixelSize(R.dimen.horizontal_margin_for_vertical),
                resources.getDimensionPixelSize(R.dimen.vertical_margin_for_vertical)
            ))
        }
    }

    private fun getMovies() {
        viewModel.getSavedMovies().observe(viewLifecycleOwner) { movies ->
            favoriteRvAdapter.submitList(movies)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}