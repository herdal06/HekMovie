package com.herdal.moviehouse.data.remote.model.person

data class PersonDto(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    val known_for: List<KnownForDto>,
    val known_for_department: String,
    val name: String,
    val popularity: Double,
    val profile_path: String?
)