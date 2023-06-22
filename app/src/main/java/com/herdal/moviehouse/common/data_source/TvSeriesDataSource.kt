package com.herdal.moviehouse.common.data_source

import androidx.paging.PagingData
import com.herdal.moviehouse.data.remote.model.tv_series.TvSeriesDto
import kotlinx.coroutines.flow.Flow

interface TvSeriesDataSource {
    interface Remote {
        fun searchTvSeries(query: String): Flow<PagingData<TvSeriesDto>>
    }
}