package com.herdal.moviehouse.ui.movies_by_genre

import androidx.paging.PagingData
import com.herdal.moviehouse.domain.uimodel.movie.MovieUiModel

data class MoviesByGenreUiState(
    val movies: PagingData<MovieUiModel>? = PagingData.empty()
)