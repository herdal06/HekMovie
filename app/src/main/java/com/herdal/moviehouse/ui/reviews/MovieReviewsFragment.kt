package com.herdal.moviehouse.ui.reviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.herdal.moviehouse.databinding.FragmentMovieReviewsBinding
import com.herdal.moviehouse.ui.reviews.adapter.ReviewAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MovieReviewsFragment(private val movieId: Int) : Fragment() {

    private var _binding: FragmentMovieReviewsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: MovieReviewsViewModel by viewModels()

    private val reviewAdapter: ReviewAdapter by lazy {
        ReviewAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieReviewsBinding.inflate(inflater, container, false)
        val view = binding.root
        setupRv()
        observeReviews(movieId)
        return view
    }

    private fun setupRv() = binding.apply {
        rvReviews.adapter = reviewAdapter
    }

    private fun observeReviews(movieId: Int) = lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.getReviews(movieId).observe(viewLifecycleOwner) {
                reviewAdapter.submitData(lifecycle, it)
                Timber.d("$it")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}