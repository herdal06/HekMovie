package com.herdal.moviehouse.ui.home.person_details

sealed class PersonDetailsUiEvent {
    data class GetPersonDetails(val id: Int) : PersonDetailsUiEvent()
}