package com.herdal.moviehouse.data.remote.data_source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.herdal.moviehouse.common.data_source.TvSeriesDataSource
import com.herdal.moviehouse.data.remote.model.tv_series.TvSeriesDto
import com.herdal.moviehouse.data.remote.paging_source.SearchTvSeriesPagingSource
import com.herdal.moviehouse.data.remote.service.TvSeriesService
import com.herdal.moviehouse.utils.ApiConstants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TvSeriesRemoteDataSource @Inject constructor(
    private val tvSeriesService: TvSeriesService
) : TvSeriesDataSource.Remote {
    override fun searchTvSeries(query: String): Flow<PagingData<TvSeriesDto>> =
        Pager(
            config = PagingConfig(
                pageSize = ApiConstants.NETWORK_PAGE_SIZE,
                prefetchDistance = 2,
                maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
                jumpThreshold = Int.MIN_VALUE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { SearchTvSeriesPagingSource(tvSeriesService, query) }
        ).flow
}