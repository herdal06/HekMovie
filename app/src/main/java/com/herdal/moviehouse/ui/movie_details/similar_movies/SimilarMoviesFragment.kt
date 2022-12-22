package com.herdal.moviehouse.ui.movie_details.similar_movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.herdal.moviehouse.databinding.FragmentSimilarMoviesBinding
import com.herdal.moviehouse.ui.home.adapter.movie.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class SimilarMoviesFragment(private val movieId: Int) : Fragment() {

    private var _binding: FragmentSimilarMoviesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: SimilarMoviesViewModel by viewModels()

    private val similarMoviesAdapter: MovieAdapter by lazy {
        MovieAdapter(::onClickMovie)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSimilarMoviesBinding.inflate(inflater, container, false)
        val view = binding.root
        setupRv()
        observeSimilarMovies(movieId)
        return view
    }

    private fun setupRv() = binding.apply {
        rvSimilar.adapter = similarMoviesAdapter
    }

    private fun observeSimilarMovies(movieId: Int) = lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.getSimilarMovies(movieId).observe(viewLifecycleOwner) {
                similarMoviesAdapter.submitData(lifecycle, it)
                Timber.d("$it")
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