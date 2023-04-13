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
import com.herdal.moviehouse.ui.home.adapter.movie.OnClickMovieListener
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

    private lateinit var moviesByGenreAdapter: MovieAdapter

    private val navigationArgs: MoviesByGenreFragmentArgs by navArgs()

    private fun getArgs() = navigationArgs.genre

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesByGenreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerViewAdapters()
        getArgs().name?.let { setToolBarTitle(it) }
        observeMovies(getArgs())
    }

    private fun initRecyclerViewAdapters() {
        moviesByGenreAdapter = MovieAdapter(object : OnClickMovieListener {
            override fun onClick(movieId: Int) {
                onClickMovie(movieId)
            }
        })
        setupRecyclerView()
    }

    private fun setupRecyclerView() = binding.apply {
        rvMoviesByGenre.adapter = moviesByGenreAdapter
    }

    private fun observeMovies(genre: GenreUiModel) = lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            genre.id?.let {
                viewModel.getMoviesByGenre(it).observe(viewLifecycleOwner) {
                    moviesByGenreAdapter.submitData(lifecycle, it)
                    Timber.d("$it")
                }
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