package com.herdal.moviehouse.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.herdal.moviehouse.R
import com.herdal.moviehouse.common.Resource
import com.herdal.moviehouse.databinding.FragmentHomeBinding
import com.herdal.moviehouse.domain.uimodel.GenreUiModel
import com.herdal.moviehouse.ui.home.adapter.genre.GenreAdapter
import com.herdal.moviehouse.utils.extensions.hide
import com.herdal.moviehouse.utils.extensions.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    private val genreAdapter: GenreAdapter by lazy {
        GenreAdapter(::onGenreClick)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        setupRecyclerViews()
        collectApiRequest()
        return binding.root
    }

    private fun setupRecyclerViews() = binding.apply {
        rvGenres.adapter = genreAdapter
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

    private fun onGenreClick(genre: GenreUiModel) {
        val action = HomeFragmentDirections.actionHomeFragmentToGenresFragment(genre = genre)
        findNavController().navigate(action)
    }
}