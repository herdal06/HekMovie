package com.herdal.moviehouse.ui.home

import androidx.paging.PagingData
import com.herdal.moviehouse.domain.uimodel.genre.GenreUiModel
import com.herdal.moviehouse.domain.uimodel.movie.MovieUiModel
import com.herdal.moviehouse.domain.uimodel.person.PersonUiModel

data class HomeUiState(
    var error: String? = null,
    val loading: Boolean? = false,
    val genres: List<GenreUiModel>? = emptyList(),
    val popularMovies: PagingData<MovieUiModel>? = PagingData.empty(),
    val topRatedMovies: PagingData<MovieUiModel>? = PagingData.empty(),
    val upcomingMovies: PagingData<MovieUiModel>? = PagingData.empty(),
    val nowPlayingMovies: PagingData<MovieUiModel>? = PagingData.empty(),
    val popularPeople: PagingData<PersonUiModel>? = PagingData.empty()
)