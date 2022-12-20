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
import com.herdal.moviehouse.common.downloadImage
import com.herdal.moviehouse.common.getPlaceHolder
import com.herdal.moviehouse.databinding.FragmentMovieDetailsBinding
import com.herdal.moviehouse.domain.uimodel.MovieDetailUiModel
import com.herdal.moviehouse.ui.home.adapter.genre.GenreAdapter
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

    private val genreAdapter: GenreAdapter by lazy {
        GenreAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        collectProductDetailRequest()
        setupRecyclerViews()
        return view
    }

    private fun setupRecyclerViews() = binding.apply {
        rvGenresDetails.adapter = genreAdapter
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
                            resource.data.let { movieDetail ->
                                setupUI(movieDetail)
                                genreAdapter.submitList(movieDetail?.genres)
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
        movieDetail?.let {
            tvTitleDetails.text = movieDetail.title
            tvVoteDetails.text = movieDetail.vote_average.toString()
            tvMovieTaglineDetails.text = movieDetail.tagline
            tvOverViewDetails.text = movieDetail.overview
            tvReleaseDateDetails.text = movieDetail.release_date
            ivPosterDetails.downloadImage(movieDetail.poster_path, getPlaceHolder(requireContext()))
            ivBackdropDetails.downloadImage(
                movieDetail.backdrop_path,
                getPlaceHolder(requireContext())
            )
            viewStar.show()
        }
    }
}