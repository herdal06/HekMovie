package com.herdal.moviehouse.ui.home.person_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.herdal.moviehouse.common.Resource
import com.herdal.moviehouse.domain.uimodel.person.PersonDetailUiModel
import com.herdal.moviehouse.domain.use_case.person.GetPersonDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PersonDetailsViewModel @Inject constructor(
    private val getPersonDetailsUseCase: GetPersonDetailsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PersonDetailsUiState())
    val uiState: StateFlow<PersonDetailsUiState> = _uiState

    fun onEvent(event: PersonDetailsUiEvent) {
        when (event) {
            is PersonDetailsUiEvent.GetPersonDetails -> getPersonDetails(event.id)
        }
    }

    private val _personDetail =
        MutableStateFlow<Resource<PersonDetailUiModel>>(Resource.Loading())
    val personDetail: StateFlow<Resource<PersonDetailUiModel>> = _personDetail

    fun getPersonDetails(id: Int) {
        getPersonDetailsUseCase.invoke(id)
            .onEach { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _personDetail.value = Resource.Loading()
                    }
                    is Resource.Success -> {
                        if (resource.data != null) {
                            _personDetail.value = Resource.Success(resource.data)
                        }
                    }
                    is Resource.Error -> {
                        _personDetail.value = Resource.Error(resource.message)
                    }
                }
            }.launchIn(viewModelScope)
    }
}