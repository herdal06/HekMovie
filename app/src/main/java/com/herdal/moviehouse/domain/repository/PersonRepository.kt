package com.herdal.moviehouse.domain.repository

import androidx.paging.PagingData
import com.herdal.moviehouse.domain.uimodel.person.PersonDetailUiModel
import com.herdal.moviehouse.domain.uimodel.person.PersonUiModel
import kotlinx.coroutines.flow.Flow

interface PersonRepository {
    fun getPopularPeople(): Flow<PagingData<PersonUiModel>>
    suspend fun getPersonDetails(id: Int): PersonDetailUiModel
}