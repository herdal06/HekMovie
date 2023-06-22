package com.herdal.moviehouse.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.herdal.moviehouse.data.data_source.TvSeriesDataSource
import com.herdal.moviehouse.common.mapper.tv_series.TvSeriesMapper
import com.herdal.moviehouse.domain.repository.TvSeriesRepository
import com.herdal.moviehouse.domain.uimodel.tv_series.TvSeriesUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TvSeriesRepositoryImpl @Inject constructor(
    private val remote: TvSeriesDataSource.Remote,
    private val tvSeriesMapper: TvSeriesMapper
) : TvSeriesRepository {
    override fun searchPeople(query: String): Flow<PagingData<TvSeriesUiModel>> {
        val domainTvSeries = remote.searchTvSeries(query = query).map { pagingData ->
            pagingData.map { searchedTvSeries ->
                tvSeriesMapper.toDomain(searchedTvSeries)
            }
        }
        return domainTvSeries
    }
}