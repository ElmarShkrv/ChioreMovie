package com.chiore.chioremovie.ui.fragments.seeallfragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.chiore.chioremovie.R
import com.chiore.chioremovie.databinding.FragmentNowPlayingBinding

class NowPlayingFragment : Fragment() {

    private var _binding: FragmentNowPlayingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentNowPlayingBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nowPlayingToolbar.inflateMenu(R.menu.sample_menu)

        binding.nowPlayingToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_back -> {
                    // Navigate to settings screen
                    true
                }
                else -> false
            }
        }

    }

    fun clearToolbarMenu() {
        binding.nowPlayingToolbar.menu.clear()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}