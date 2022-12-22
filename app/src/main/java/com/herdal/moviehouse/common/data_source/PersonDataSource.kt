package com.herdal.moviehouse.common.data_source

import androidx.paging.PagingData
import com.herdal.moviehouse.data.remote.model.person.PersonDto
import kotlinx.coroutines.flow.Flow

interface PersonDataSource {
    interface Remote {
        fun getPopularPeople(): Flow<PagingData<PersonDto>>
    }
}