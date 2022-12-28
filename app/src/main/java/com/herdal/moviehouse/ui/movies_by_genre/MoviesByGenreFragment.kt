package com.herdal.moviehouse.ui.movies_by_genre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.herdal.moviehouse.databinding.FragmentMoviesByGenreBinding
import com.herdal.moviehouse.domain.uimodel.genre.GenreUiModel
import com.herdal.moviehouse.ui.home.adapter.movie.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MoviesByGenreFragment : Fragment() {

    private var _binding: FragmentMoviesByGenreBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: MoviesByGenreViewModel by viewModels()

    private val moviesByGenreAdapter: MovieAdapter by lazy {
        MovieAdapter(::onClickMovie)
    }

    private val navigationArgs: MoviesByGenreFragmentArgs by navArgs()

    private fun getArgs() = navigationArgs.genre

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesByGenreBinding.inflate(inflater, container, false)
        val view = binding.root
        setupRecyclerView()
        setToolBarTitle(getArgs().name)
        observeMovies(getArgs())
        return view
    }

    private fun setupRecyclerView() = binding.apply {
        rvMoviesByGenre.adapter = moviesByGenreAdapter
    }

    private fun observeMovies(genre: GenreUiModel) = lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.getMoviesByGenre(genre.id).observe(viewLifecycleOwner) {
                moviesByGenreAdapter.submitData(lifecycle, it)
                Timber.d("$it")
            }
        }
    }

    private fun setToolBarTitle(genreName: String) {
        (activity as AppCompatActivity).supportActionBar?.title = genreName
    }

    private fun onClickMovie(movieId: Int) {
        val action =
            MoviesByGenreFragmentDirections.actionMoviesByGenreFragmentToMovieDetailsFragment(
                movieId
            )
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}