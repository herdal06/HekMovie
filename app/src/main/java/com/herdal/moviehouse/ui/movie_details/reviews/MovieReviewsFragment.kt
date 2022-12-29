package com.herdal.moviehouse.ui.movie_details.reviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import com.herdal.moviehouse.databinding.FragmentMovieReviewsBinding
import com.herdal.moviehouse.ui.movie_details.reviews.adapter.ReviewAdapter
import com.herdal.moviehouse.ui.movie_details.reviews.adapter.ReviewItemDecorator
import com.herdal.moviehouse.utils.extensions.hide
import com.herdal.moviehouse.utils.extensions.show
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
    ): View {
        _binding = FragmentMovieReviewsBinding.inflate(inflater, container, false)
        val view = binding.root
        setupRv()
        observeReviews(movieId)
        checkIfAdapterEmpty()
        return view
    }

    private fun setupRv() = binding.apply {
        rvReviews.adapter = reviewAdapter
        rvReviews.addItemDecoration(ReviewItemDecorator(requireContext()))
        rvReviews.addItemDecoration(
            DividerItemDecoration(
                rvReviews.context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun observeReviews(movieId: Int) = lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.getReviews(movieId).observe(viewLifecycleOwner) {
                reviewAdapter.submitData(lifecycle, it)
                Timber.d("$it")
            }
        }
    }

    private fun checkIfAdapterEmpty() = binding.apply {
        reviewAdapter.addLoadStateListener {
            if (it.append.endOfPaginationReached) {
                if (reviewAdapter.itemCount < 1) {
                    tvReviewEmpty.show()
                    rvReviews.hide()
                    ivReviewNoData.show()
                } else {
                    rvReviews.show()
                    tvReviewEmpty.hide()
                    ivReviewNoData.hide()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}