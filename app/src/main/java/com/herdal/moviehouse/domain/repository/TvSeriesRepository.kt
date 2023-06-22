package com.herdal.moviehouse.domain.repository

import androidx.paging.PagingData
import com.herdal.moviehouse.domain.uimodel.tv_series.TvSeriesUiModel
import kotlinx.coroutines.flow.Flow

interface TvSeriesRepository {
    fun searchPeople(query: String): Flow<PagingData<TvSeriesUiModel>>
}