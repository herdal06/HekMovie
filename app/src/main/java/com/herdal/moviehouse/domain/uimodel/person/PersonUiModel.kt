package com.herdal.moviehouse.domain.uimodel.person

data class PersonUiModel(
    val gender: Int,
    val id: Int,
    val known_for: List<KnowForUiModel>,
    val known_for_department: String,
    val name: String,
    val popularity: Double,
    val profile_path: String?
)