package com.herdal.moviehouse.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.herdal.moviehouse.databinding.FragmentSearchBinding
import com.herdal.moviehouse.ui.home.adapter.movie.MovieAdapter
import com.herdal.moviehouse.ui.home.adapter.people.PersonAdapter
import com.herdal.moviehouse.ui.tv_series.TvSeriesAdapter
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
    private val searchTvSeriesAdapter: TvSeriesAdapter by lazy { TvSeriesAdapter(::onClickTvSeries) }

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
                binding.rvSearch.layoutManager = GridLayoutManager(requireContext(), 3)
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
                binding.rvSearch.layoutManager = GridLayoutManager(requireContext(), 2)
                flow.collect { searchedPeople ->
                    searchPersonAdapter.submitData(searchedPeople)
                }
            }
        }
    }

    private fun searchTvSeries(searchText: String) {
        viewModel.handleEvent(SearchUiEvent.SearchTvSeries(searchText))
        collectLatestLifecycleFlow(viewModel.uiState) { state ->
            state.tvSeries?.let { flow ->
                binding.rvSearch.show()
                binding.rvSearch.adapter = searchTvSeriesAdapter
                binding.rvSearch.layoutManager = LinearLayoutManager(requireContext())
                flow.collect { searchedTvSeries ->
                    searchTvSeriesAdapter.submitData(searchedTvSeries)
                }
            }
        }
    }

    private fun setupSearchView() {
        val queryTextListener = object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    when {
                        binding.chipMovies.isChecked -> searchMovies(query)
                        binding.chipPeople.isChecked -> searchPeople(query)
                        binding.chipTvSeries.isChecked -> searchTvSeries(query)
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        }

        binding.searchView.setOnQueryTextListener(queryTextListener)

        val chipCheckedChangeListener = CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                when (buttonView) {
                    binding.chipMovies -> {
                        binding.chipPeople.isChecked = false
                        binding.chipTvSeries.isChecked = false
                    }
                    binding.chipPeople -> {
                        binding.chipMovies.isChecked = false
                        binding.chipTvSeries.isChecked = false
                    }
                    binding.chipTvSeries -> {
                        binding.chipMovies.isChecked = false
                        binding.chipPeople.isChecked = false
                    }
                }

                val query = binding.searchView.query.toString()
                if (query.isNotEmpty()) {
                    when {
                        binding.chipMovies.isChecked -> searchMovies(query)
                        binding.chipPeople.isChecked -> searchPeople(query)
                        binding.chipTvSeries.isChecked -> searchTvSeries(query)
                    }
                }
            }
        }

        binding.chipMovies.setOnCheckedChangeListener(chipCheckedChangeListener)
        binding.chipPeople.setOnCheckedChangeListener(chipCheckedChangeListener)
        binding.chipTvSeries.setOnCheckedChangeListener(chipCheckedChangeListener)
    }

    private fun onClickMovie(movieId: Int) {
        val action = SearchFragmentDirections.actionSearchFragmentToMovieDetailsFragment(movieId)
        findNavController().navigate(action)
    }

    private fun onClickPerson(personId: Int) {
        val action = SearchFragmentDirections.actionSearchFragmentToPersonDetailsFragment(personId)
        findNavController().navigate(action)
    }

    private fun onClickTvSeries(tvSeriesId: Int) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}