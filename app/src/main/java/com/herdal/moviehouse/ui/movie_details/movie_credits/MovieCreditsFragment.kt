package com.herdal.moviehouse.ui.movie_details.movie_credits

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.herdal.moviehouse.common.Resource
import com.herdal.moviehouse.databinding.FragmentMovieCreditsBinding
import com.herdal.moviehouse.ui.movie_details.movie_credits.adapter.CastAdapter
import com.herdal.moviehouse.utils.extensions.hide
import com.herdal.moviehouse.utils.extensions.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieCreditsFragment(private val movieId: Int) : Fragment() {

    private var _binding: FragmentMovieCreditsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: MovieCreditsViewModel by viewModels()

    private val castAdapter: CastAdapter by lazy {
        CastAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieCreditsBinding.inflate(inflater, container, false)
        val view = binding.root
        setupRecyclerView()
        collectApiRequest()
        return view
    }

    private fun collectApiRequest() = binding.apply {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    // genres
                    viewModel.getMovieCredits(movieId)
                    viewModel.movieCredits.collect { res ->
                        when (res) {
                            is Resource.Loading -> {
                                tvCastErrorMessage.hide()
                                rvCast.hide()
                            }
                            is Resource.Success -> {
                                tvCastErrorMessage.hide()
                                pbCast.hide()
                                rvCast.show()
                                castAdapter.submitList(res.data?.cast)
                            }
                            is Resource.Error -> {
                                tvCastErrorMessage.show()
                                pbCast.hide()
                                rvCast.hide()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() = binding.apply {
        rvCast.adapter = castAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}