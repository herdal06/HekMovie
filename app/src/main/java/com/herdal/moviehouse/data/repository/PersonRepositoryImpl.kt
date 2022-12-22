package com.herdal.moviehouse.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.herdal.moviehouse.common.data_source.PersonDataSource
import com.herdal.moviehouse.common.mapper.person.PersonMapper
import com.herdal.moviehouse.domain.repository.PersonRepository
import com.herdal.moviehouse.domain.uimodel.person.PersonUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    private val personMapper: PersonMapper,
    private val remote: PersonDataSource.Remote
) : PersonRepository {
    override fun getPopularPeople(): Flow<PagingData<PersonUiModel>> {
        val domainPopularPerson = remote.getPopularPeople().map { pagingData ->
            pagingData.map { domainPopularPerson ->
                personMapper.toDomain(domainPopularPerson)
            }
        }
        Timber.d("popular: $domainPopularPerson")
        return domainPopularPerson
    }
}