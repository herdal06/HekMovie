package com.herdal.moviehouse.ui.movie_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.herdal.moviehouse.common.Resource
import com.herdal.moviehouse.databinding.FragmentMovieDetailsBinding
import com.herdal.moviehouse.domain.uimodel.MovieDetailUiModel
import com.herdal.moviehouse.utils.extensions.hide
import com.herdal.moviehouse.utils.extensions.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private val binding get() = _binding!!

    private var _binding: FragmentMovieDetailsBinding? = null

    private val viewModel: MovieDetailsViewModel by viewModels()

    private val navigationArgs: MovieDetailsFragmentArgs by navArgs()

    private fun getArgs() = navigationArgs.movieId

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        collectProductDetailRequest()
        return view
    }

    private fun collectProductDetailRequest() = binding.apply {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getMovieDetails(getArgs())
                viewModel.movieDetail.collectLatest { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            pbMovieDetail.show()
                            tvMovieDetailErrorMessage.hide()
                        }
                        is Resource.Success -> {
                            resource.data.let {
                                setupUI(it)
                            }
                            tvMovieDetailErrorMessage.hide()
                            pbMovieDetail.hide()

                        }
                        is Resource.Error -> {
                            pbMovieDetail.hide()
                            tvMovieDetailErrorMessage.show()
                        }
                    }
                }
            }
        }
    }

    private fun setupUI(movieDetail: MovieDetailUiModel?) = binding.apply {

    }
}