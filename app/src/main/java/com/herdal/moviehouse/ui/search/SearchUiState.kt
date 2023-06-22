package com.herdal.moviehouse.ui.search

import androidx.paging.PagingData
import com.herdal.moviehouse.domain.uimodel.movie.MovieUiModel
import com.herdal.moviehouse.domain.uimodel.person.PersonUiModel
import com.herdal.moviehouse.domain.uimodel.tv_series.TvSeriesUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class SearchUiState(
    val loading: Boolean = false,
    val error: String? = null,
    val search: String? = null,
    val movies: Flow<PagingData<MovieUiModel>>? = emptyFlow(),
    val people: Flow<PagingData<PersonUiModel>>? = emptyFlow(),
    val tvSeries: Flow<PagingData<TvSeriesUiModel>>? = emptyFlow()
)