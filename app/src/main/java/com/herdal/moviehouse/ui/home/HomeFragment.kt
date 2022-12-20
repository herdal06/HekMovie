package com.herdal.moviehouse.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.herdal.moviehouse.R
import com.herdal.moviehouse.common.Resource
import com.herdal.moviehouse.databinding.FragmentHomeBinding
import com.herdal.moviehouse.ui.home.adapter.genre.GenreAdapter
import com.herdal.moviehouse.ui.home.adapter.movie.MovieAdapter
import com.herdal.moviehouse.utils.extensions.hide
import com.herdal.moviehouse.utils.extensions.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    private val genreAdapter: GenreAdapter by lazy {
        GenreAdapter()
    }

    private val popularMoviesAdapter: MovieAdapter by lazy {
        MovieAdapter(::onClickMovie)
    }

    private val topRatedMoviesAdapter: MovieAdapter by lazy {
        MovieAdapter(::onClickMovie)
    }

    private val nowPlayingMoviesAdapter: MovieAdapter by lazy {
        MovieAdapter(::onClickMovie)
    }

    private val upcomingMoviesAdapter: MovieAdapter by lazy {
        MovieAdapter(::onClickMovie)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        setupRecyclerViews()
        observeMovies()
        collectApiRequest()
        return binding.root
    }

    private fun setupRecyclerViews() = binding.apply {
        rvGenres.adapter = genreAdapter
        rvPopularMovies.adapter = popularMoviesAdapter
        rvUpcomingMovies.adapter = upcomingMoviesAdapter
        rvNowPlayingMovies.adapter = nowPlayingMoviesAdapter
        rvTopRatedMovies.adapter = topRatedMoviesAdapter
    }

    private fun collectApiRequest() = binding.apply {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    // genres
                    viewModel.getAllGenres()
                    viewModel.genres.collect { res ->
                        when (res) {
                            is Resource.Loading -> {
                                tvGenreErrorMessage.hide()
                                rvGenres.hide()
                            }
                            is Resource.Success -> {
                                tvGenreErrorMessage.hide()
                                pbGenres.hide()
                                rvGenres.show()
                                genreAdapter.submitList(res.data)
                            }
                            is Resource.Error -> {
                                tvGenreErrorMessage.show()
                                pbGenres.hide()
                                rvGenres.hide()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun observeMovies() = lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.getPopularMovies().observe(viewLifecycleOwner) {
                popularMoviesAdapter.submitData(lifecycle, it)
                Timber.d("$it")
            }
            viewModel.getTopRatedMovies().observe(viewLifecycleOwner) {
                topRatedMoviesAdapter.submitData(lifecycle, it)
                Timber.d("$it")
            }
            viewModel.getUpcomingMovies().observe(viewLifecycleOwner) {
                upcomingMoviesAdapter.submitData(lifecycle, it)
                Timber.d("$it")
            }
            viewModel.getNowPlayingMovies().observe(viewLifecycleOwner) {
                nowPlayingMoviesAdapter.submitData(lifecycle, it)
                Timber.d("$it")
            }
        }
    }

    private fun onClickMovie(movieId: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(movieId)
        findNavController().navigate(action)
    }
}