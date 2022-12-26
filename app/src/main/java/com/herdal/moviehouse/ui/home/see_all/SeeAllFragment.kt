package com.herdal.moviehouse.ui.home.see_all

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.herdal.moviehouse.databinding.FragmentSeeAllBinding
import com.herdal.moviehouse.ui.home.HomeViewModel
import com.herdal.moviehouse.ui.home.adapter.movie.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SeeAllFragment : Fragment() {

    private var _binding: FragmentSeeAllBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private val navigationArgs: SeeAllFragmentArgs by navArgs()

    private fun getArgs() = navigationArgs.type

    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter(::onClickMovie)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSeeAllBinding.inflate(inflater, container, false)
        val view = binding.root
        setupRecyclerView()
        observeData(getArgs())
        changeToolBarTitle(getArgs())
        return view
    }

    private fun setupRecyclerView() = binding.apply {
        rvSeeAll.adapter = movieAdapter
    }

    private fun changeToolBarTitle(text: String) = binding.apply {
        toolbar.title = text
    }

    private fun observeData(type: String) = lifecycleScope.launch {
        when (type) {
            "Popular" -> {
                viewModel.getPopularMovies().observe(viewLifecycleOwner) {
                    movieAdapter.submitData(lifecycle, it)
                }
            }
            "Upcoming" -> {
                viewModel.getUpcomingMovies().observe(viewLifecycleOwner) {
                    movieAdapter.submitData(lifecycle, it)
                }
            }
            "Top Rated" -> {
                viewModel.getTopRatedMovies().observe(viewLifecycleOwner) {
                    movieAdapter.submitData(lifecycle, it)
                }
            }
            "Now Playing" -> {
                viewModel.getNowPlayingMovies().observe(viewLifecycleOwner) {
                    movieAdapter.submitData(lifecycle, it)
                }
            }
        }
    }

    private fun onClickMovie(movieId: Int) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}