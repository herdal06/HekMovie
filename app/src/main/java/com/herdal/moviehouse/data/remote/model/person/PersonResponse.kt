package com.herdal.moviehouse.data.remote.model.person

data class PersonResponse(
    val page: Int,
    val results: List<PersonDto>,
    val total_pages: Int,
    val total_results: Int
)