package com.herdal.moviehouse.ui.genres

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.herdal.moviehouse.common.Resource
import com.herdal.moviehouse.databinding.FragmentGenresBinding
import com.herdal.moviehouse.domain.uimodel.GenreUiModel
import com.herdal.moviehouse.ui.genres.adapter.GenresAdapter
import com.herdal.moviehouse.utils.extensions.hide
import com.herdal.moviehouse.utils.extensions.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GenresFragment : Fragment() {

    private var _binding: FragmentGenresBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val genreAdapter: GenresAdapter by lazy {
        GenresAdapter(::onClickGenre)
    }

    private val viewModel: GenresViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGenresBinding.inflate(inflater, container, false)
        val view = binding.root
        setupRecyclerView()
        collectGenres()
        return view
    }

    private fun collectGenres() = binding.apply {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.getAllGenres()
                    viewModel.genres.collect { res ->
                        when (res) {
                            is Resource.Loading -> {
                                tvGenresErrorMessage.hide()
                                rvAllGenre.hide()
                            }
                            is Resource.Success -> {
                                tvGenresErrorMessage.hide()
                                pbGenre.hide()
                                rvAllGenre.show()
                                genreAdapter.submitList(res.data)
                            }
                            is Resource.Error -> {
                                tvGenresErrorMessage.show()
                                pbGenre.hide()
                                rvAllGenre.hide()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() = binding.apply {
        rvAllGenre.adapter = genreAdapter
    }

    private fun onClickGenre(genre: GenreUiModel) {
        val action = GenresFragmentDirections.actionGenresFragmentToMoviesByGenreFragment(genre)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}