package com.herdal.moviehouse.data.remote.data_source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.herdal.moviehouse.data.data_source.PersonDataSource
import com.herdal.moviehouse.data.remote.model.person.PersonDto
import com.herdal.moviehouse.data.remote.model.person_detail.PersonDetailDto
import com.herdal.moviehouse.data.remote.paging_source.PersonPagingSource
import com.herdal.moviehouse.data.remote.service.PersonService
import com.herdal.moviehouse.utils.ApiConstants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PersonRemoteDataSource @Inject constructor(
    private val personService: PersonService
) : PersonDataSource.Remote {
    override fun getPopularPeople(): Flow<PagingData<PersonDto>> =
        Pager(
            config = PagingConfig(
                pageSize = ApiConstants.NETWORK_PAGE_SIZE,
                prefetchDistance = 2,
                maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
                jumpThreshold = Int.MIN_VALUE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { PersonPagingSource(personService) }
        ).flow

    override suspend fun getPersonDetails(id: Int): PersonDetailDto =
        personService.getPersonDetails(id)
}