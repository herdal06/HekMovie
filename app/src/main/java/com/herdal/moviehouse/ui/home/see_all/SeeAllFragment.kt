package com.herdal.moviehouse.ui.home.see_all

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.herdal.moviehouse.R
import com.herdal.moviehouse.databinding.FragmentSeeAllBinding
import com.herdal.moviehouse.ui.home.HomeViewModel
import com.herdal.moviehouse.ui.home.adapter.movie.MovieAdapter
import com.herdal.moviehouse.ui.home.adapter.people.PersonAdapter
import com.herdal.moviehouse.utils.extensions.observeOnce
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SeeAllFragment : Fragment() {

    private var _binding: FragmentSeeAllBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private val navigationArgs: SeeAllFragmentArgs by navArgs()

    private fun getArgs() = navigationArgs.type

    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter(::onClickMovie)
    }

    private val peopleAdapter: PersonAdapter by lazy {
        PersonAdapter(::onClickPerson)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSeeAllBinding.inflate(inflater, container, false)
        val view = binding.root
        observeData(getArgs())
        changeToolBarTitle(getArgs())
        return view
    }

    private fun setupRvMovie() = binding.apply {
        rvSeeAll.adapter = movieAdapter
    }

    private fun setupRvPeople() = binding.apply {
        rvSeeAll.adapter = peopleAdapter
    }

    private fun changeToolBarTitle(text: String) = binding.apply {
        toolbar.title = text
    }

    private fun observeData(type: String) = lifecycleScope.launch {
        when (type) {
            getString(R.string.popular) -> {
                viewModel.getPopularMovies().observeOnce(viewLifecycleOwner) {
                    setupRvMovie()
                    movieAdapter.submitData(lifecycle, it)
                }
            }
            getString(R.string.upcoming) -> {
                viewModel.getUpcomingMovies().observeOnce(viewLifecycleOwner) {
                    setupRvMovie()
                    movieAdapter.submitData(lifecycle, it)
                }
            }
            getString(R.string.top_rated) -> {
                viewModel.getTopRatedMovies().observeOnce(viewLifecycleOwner) {
                    setupRvMovie()
                    movieAdapter.submitData(lifecycle, it)
                }
            }
            getString(R.string.now_playing) -> {
                viewModel.getNowPlayingMovies().observeOnce(viewLifecycleOwner) {
                    setupRvMovie()
                    movieAdapter.submitData(lifecycle, it)
                }
            }
            getString(R.string.popular) -> {
                viewModel.getPopularPeople().observeOnce(viewLifecycleOwner) {
                    setupRvPeople()
                    peopleAdapter.submitData(lifecycle, it)
                }
            }
        }
    }

    private fun onClickMovie(movieId: Int) {

    }

    private fun onClickPerson(personId: Int) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}