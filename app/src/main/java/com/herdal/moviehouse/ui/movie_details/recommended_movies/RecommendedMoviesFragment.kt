package com.herdal.moviehouse.ui.movie_details.recommended_movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.herdal.moviehouse.databinding.FragmentRecommendedMoviesBinding
import com.herdal.moviehouse.ui.home.adapter.movie.MovieAdapter
import com.herdal.moviehouse.ui.home.adapter.movie.OnClickMovieListener
import com.herdal.moviehouse.utils.extensions.hide
import com.herdal.moviehouse.utils.extensions.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class RecommendedMoviesFragment(
    private val movieId: Int
) : Fragment() {

    private var _binding: FragmentRecommendedMoviesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: RecommendedMoviesViewModel by viewModels()

    private lateinit var recommendedMoviesAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecommendedMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerViewAdapters()
        observeSimilarMovies(movieId)
        checkIfAdapterEmpty()
    }

    private fun initRecyclerViewAdapters() {
        recommendedMoviesAdapter = MovieAdapter(object : OnClickMovieListener {
            override fun onClick(movieId: Int) {
                onClickMovie(movieId)
            }
        })
        setupRv()
    }

    private fun setupRv() = binding.apply {
        rvRecommended.adapter = recommendedMoviesAdapter
    }

    private fun observeSimilarMovies(movieId: Int) = lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.getRecommendedMovies(movieId).observe(viewLifecycleOwner) {
                recommendedMoviesAdapter.submitData(lifecycle, it)
                Timber.d("$it")
            }
        }
    }

    private fun checkIfAdapterEmpty() = binding.apply {
        recommendedMoviesAdapter.addLoadStateListener {
            if (it.append.endOfPaginationReached) {
                if (recommendedMoviesAdapter.itemCount < 1) {
                    tvRecommendedEmpty.show()
                    rvRecommended.hide()
                    ivRecommendedNoData.show()
                } else {
                    rvRecommended.show()
                    tvRecommendedEmpty.hide()
                    ivRecommendedNoData.hide()
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