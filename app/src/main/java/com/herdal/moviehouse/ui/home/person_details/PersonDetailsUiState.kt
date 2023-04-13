package com.herdal.moviehouse.ui.home.person_details

import com.herdal.moviehouse.domain.uimodel.person.PersonDetailUiModel

data class PersonDetailsUiState(
    var error: String? = null,
    val loading: Boolean? = false,
    val personDetail: PersonDetailUiModel? = null
)