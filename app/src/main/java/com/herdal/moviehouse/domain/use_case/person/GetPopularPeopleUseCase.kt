package com.herdal.moviehouse.domain.use_case.person

import androidx.paging.PagingData
import com.herdal.moviehouse.domain.repository.PersonRepository
import com.herdal.moviehouse.domain.uimodel.person.PersonUiModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularPeopleUseCase @Inject constructor(
    private val personRepository: PersonRepository
) {
    operator fun invoke(): Flow<PagingData<PersonUiModel>> {
        return personRepository.getPopularPeople()
    }
}