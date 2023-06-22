package com.herdal.moviehouse.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.herdal.moviehouse.databinding.FragmentSearchBinding
import com.herdal.moviehouse.ui.home.adapter.movie.MovieAdapter
import com.herdal.moviehouse.ui.home.adapter.people.PersonAdapter
import com.herdal.moviehouse.utils.extensions.collectLatestLifecycleFlow
import com.herdal.moviehouse.utils.extensions.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()

    private val searchMovieAdapter: MovieAdapter by lazy { MovieAdapter(::onClickMovie) }
    private val searchPersonAdapter: PersonAdapter by lazy { PersonAdapter(::onClickPerson) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        setupSearchView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRvSearch()
    }

    private fun setupRvSearch() {
        binding.rvSearch.adapter = searchMovieAdapter
    }

    private fun searchMovies(searchText: String) {
        viewModel.handleEvent(SearchUiEvent.SearchMovies(searchText))
        collectLatestLifecycleFlow(viewModel.uiState) { state ->
            state.movies?.let { flow ->
                binding.rvSearch.show()
                binding.rvSearch.adapter = searchMovieAdapter
                flow.collect { searchedMovies ->
                    searchMovieAdapter.submitData(searchedMovies)
                }
            }
        }
    }

    private fun searchPeople(searchText: String) {
        viewModel.handleEvent(SearchUiEvent.SearchPeople(searchText))
        collectLatestLifecycleFlow(viewModel.uiState) { state ->
            state.people?.let { flow ->
                binding.rvSearch.show()
                binding.rvSearch.adapter = searchPersonAdapter
                flow.collect { searchedPeople ->
                    searchPersonAdapter.submitData(searchedPeople)
                }
            }
        }
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    when {
                        binding.chipMovies.isChecked -> searchMovies(query)
                        binding.chipPeople.isChecked -> searchPeople(query)
                        //binding.chipTvSeries.isChecked -> searchTvSeries(query)
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        binding.chipMovies.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.chipPeople.isChecked = false
                binding.chipTvSeries.isChecked = false
            }
        }

        binding.chipPeople.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.chipMovies.isChecked = false
                binding.chipTvSeries.isChecked = false
            }
        }

        binding.chipTvSeries.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.chipMovies.isChecked = false
                binding.chipPeople.isChecked = false
            }
        }
    }

    private fun onClickMovie(movieId: Int) {
        val action = SearchFragmentDirections.actionSearchFragmentToMovieDetailsFragment(movieId)
        findNavController().navigate(action)
    }

    private fun onClickPerson(personId: Int) {
        val action = SearchFragmentDirections.actionSearchFragmentToPersonDetailsFragment(personId)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}