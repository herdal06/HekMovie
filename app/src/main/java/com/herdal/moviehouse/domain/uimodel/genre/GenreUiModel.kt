package com.herdal.moviehouse.domain.uimodel.genre

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenreUiModel(
    val id: Int,
    val name: String
) : Parcelable