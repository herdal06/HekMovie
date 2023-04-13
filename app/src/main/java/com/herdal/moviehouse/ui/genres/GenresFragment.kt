package com.herdal.moviehouse.ui.genres

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.herdal.moviehouse.R
import com.herdal.moviehouse.databinding.FragmentGenresBinding
import com.herdal.moviehouse.domain.uimodel.genre.GenreUiModel
import com.herdal.moviehouse.ui.genres.adapter.GenresAdapter
import com.herdal.moviehouse.utils.extensions.collectLatestLifecycleFlow
import com.herdal.moviehouse.utils.extensions.hide
import com.herdal.moviehouse.utils.extensions.show
import dagger.hilt.android.AndroidEntryPoint

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        collectGenres()
        setToolBarTitle()
    }

    private fun collectGenres() = binding.apply {
        viewModel.onEvent(GenresUiEvent.GetAllGenres)
        collectLatestLifecycleFlow(viewModel.uiState) { state ->
            state.error.let {
                tvGenresErrorMessage.show()
                pbGenre.hide()
                rvAllGenre.hide()
            }
            state.loading.let { isLoading ->
                if (isLoading == true) {
                    tvGenresErrorMessage.hide()
                    rvAllGenre.hide()
                }
            }
            state.genres?.let {
                tvGenresErrorMessage.hide()
                pbGenre.hide()
                rvAllGenre.show()
                genreAdapter.submitList(it)
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

    private fun setToolBarTitle() {
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.genres)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}