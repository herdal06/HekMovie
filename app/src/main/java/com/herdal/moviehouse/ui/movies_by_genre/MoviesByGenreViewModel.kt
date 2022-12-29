package com.herdal.moviehouse.ui.movies_by_genre

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.herdal.moviehouse.domain.uimodel.movie.MovieUiModel
import com.herdal.moviehouse.domain.use_case.movie.GetMoviesByGenreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MoviesByGenreViewModel @Inject constructor(
    private val getMoviesByGenreUseCase: GetMoviesByGenreUseCase
) : ViewModel() {

    private val _movies =
        MutableLiveData<PagingData<MovieUiModel>>()

    fun getMoviesByGenre(genreId: Int): LiveData<PagingData<MovieUiModel>> {
        val response = getMoviesByGenreUseCase.invoke(genreId).cachedIn(viewModelScope).asLiveData()
        _movies.value = response.value
        Timber.d("$response")
        return response
    }
}