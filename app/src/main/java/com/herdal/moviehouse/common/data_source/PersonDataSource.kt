package com.herdal.moviehouse.common.data_source

import androidx.paging.PagingData
import com.herdal.moviehouse.data.remote.model.person.PersonDto
import com.herdal.moviehouse.data.remote.model.person_detail.PersonDetailDto
import kotlinx.coroutines.flow.Flow

interface PersonDataSource {
    interface Remote {
        fun getPopularPeople(): Flow<PagingData<PersonDto>>
        suspend fun getPersonDetails(id: Int): PersonDetailDto
    }
}