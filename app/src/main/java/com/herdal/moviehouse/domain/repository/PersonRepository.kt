package com.herdal.moviehouse.domain.repository

import androidx.paging.PagingData
import com.herdal.moviehouse.domain.uimodel.person.PersonUiModel
import kotlinx.coroutines.flow.Flow

interface PersonRepository {
    fun getPopularPeople(): Flow<PagingData<PersonUiModel>>
}