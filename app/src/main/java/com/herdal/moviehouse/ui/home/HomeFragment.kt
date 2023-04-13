package com.herdal.moviehouse.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.herdal.moviehouse.R
import com.herdal.moviehouse.common.Resource
import com.herdal.moviehouse.databinding.FragmentHomeBinding
import com.herdal.moviehouse.domain.uimodel.genre.GenreUiModel
import com.herdal.moviehouse.ui.home.adapter.genre.GenreAdapter
import com.herdal.moviehouse.ui.home.adapter.genre.OnClickGenreListener
import com.herdal.moviehouse.ui.home.adapter.movie.MovieAdapter
import com.herdal.moviehouse.ui.home.adapter.people.PersonAdapter
import com.herdal.moviehouse.ui.home.adapter.movie.OnClickMovieListener
import com.herdal.moviehouse.ui.home.adapter.people.OnClickPersonListener
import com.herdal.moviehouse.utils.extensions.hide
import com.herdal.moviehouse.utils.extensions.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    private lateinit var popularMoviesAdapter: MovieAdapter
    private lateinit var topRatedMoviesAdapter: MovieAdapter
    private lateinit var nowPlayingMoviesAdapter: MovieAdapter
    private lateinit var upcomingMoviesAdapter: MovieAdapter
    private lateinit var popularPeopleAdapter: PersonAdapter
    private lateinit var genreAdapter: GenreAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerViewAdapters()
        observeMovies()
        observePeople()
        setToolBarTitle()
        collectApiRequest()
        onClickShowGenresTextView()
        onClickSeeAllTexts()
    }

    private fun initRecyclerViewAdapters() {
        popularMoviesAdapter = MovieAdapter(object : OnClickMovieListener {
            override fun onClick(movieId: Int) {
                onClickMovie(movieId)
            }
        })
        topRatedMoviesAdapter = MovieAdapter(object : OnClickMovieListener {
            override fun onClick(movieId: Int) {
                onClickMovie(movieId)
            }
        })
        nowPlayingMoviesAdapter = MovieAdapter(object : OnClickMovieListener {
            override fun onClick(movieId: Int) {
                onClickMovie(movieId)
            }
        })
        upcomingMoviesAdapter = MovieAdapter(object : OnClickMovieListener {
            override fun onClick(movieId: Int) {
                onClickMovie(movieId)
            }
        })
        popularPeopleAdapter = PersonAdapter(object : OnClickPersonListener {
            override fun onClick(personId: Int) {
                onClickPerson(personId)
            }
        })
        genreAdapter = GenreAdapter(object : OnClickGenreListener {
            override fun onClick(genre: GenreUiModel) {
                onClickGenre(genre)
            }
        })
        setupRecyclerViews()
    }

    private fun setupRecyclerViews() = binding.apply {
        rvGenres.adapter = genreAdapter
        rvPopularMovies.adapter = popularMoviesAdapter
        rvUpcomingMovies.adapter = upcomingMoviesAdapter
        rvNowPlayingMovies.adapter = nowPlayingMoviesAdapter
        rvTopRatedMovies.adapter = topRatedMoviesAdapter
        rvPopularPeople.adapter = popularPeopleAdapter
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

    private fun observePeople() = lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.getPopularPeople().observe(viewLifecycleOwner) {
                popularPeopleAdapter.submitData(lifecycle, it)
                Timber.d("$it")
            }
        }
    }

    private fun onClickMovie(movieId: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(movieId)
        findNavController().navigate(action)
    }

    private fun onClickPerson(personId: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToPersonDetailsFragment(personId)
        findNavController().navigate(action)
    }

    private fun clickTextView(textView: TextView, type: String) = textView.setOnClickListener {
        navigateToSeeAll(type)
    }

    private fun onClickSeeAllTexts() = binding.apply {
        clickTextView(tvShowPopularMovies, getString(R.string.popular_movies))
        clickTextView(tvShowUpcomingMovies, getString(R.string.upcoming_movies))
        clickTextView(tvTopRatedMovies, getString(R.string.top_rated_movies))
        clickTextView(tvShowNowPlayingMovies, getString(R.string.now_playing_movies))
        clickTextView(tvPopularPeople, getString(R.string.popular_people))
    }

    private fun onClickShowGenresTextView() = binding.apply {
        tvShowGenres.setOnClickListener {
            findNavController().navigate(R.id.genresFragment)
        }
    }

    private fun navigateToSeeAll(type: String) {
        val action = HomeFragmentDirections.actionHomeFragmentToSeeAllFragment(type)
        findNavController().navigate(action)
    }

    private fun navigateToMoviesByGenreScreen(genre: GenreUiModel) {
        val action = HomeFragmentDirections.actionHomeFragmentToMoviesByGenreFragment(genre)
        findNavController().navigate(action)
    }

    private fun onClickGenre(genre: GenreUiModel) {
        navigateToMoviesByGenreScreen(genre)
    }

    private fun setToolBarTitle() {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.home)
    }
}