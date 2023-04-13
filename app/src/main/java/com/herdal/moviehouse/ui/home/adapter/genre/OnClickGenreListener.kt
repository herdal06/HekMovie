package com.herdal.moviehouse.ui.home.adapter.genre

import com.herdal.moviehouse.domain.uimodel.genre.GenreUiModel

interface OnClickGenreListener {
    fun onClick(genre: GenreUiModel)
}