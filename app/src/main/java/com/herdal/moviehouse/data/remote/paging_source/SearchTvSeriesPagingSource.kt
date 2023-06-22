package com.herdal.moviehouse.data.remote.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.herdal.moviehouse.data.remote.model.tv_series.TvSeriesDto
import com.herdal.moviehouse.data.remote.service.TvSeriesService
import com.herdal.moviehouse.utils.ApiConstants
import okio.IOException
import retrofit2.HttpException

class SearchTvSeriesPagingSource(
    private val tvSeriesService: TvSeriesService,
    private val query: String
) : PagingSource<Int, TvSeriesDto>() {
    override fun getRefreshKey(state: PagingState<Int, TvSeriesDto>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvSeriesDto> {
        val nextPage = params.key ?: ApiConstants.STARTING_PAGE

        return try {
            val response = tvSeriesService.searchTvSeries(
                page = nextPage,
                query = query
            )

            LoadResult.Page(
                data = response.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (nextPage < response.total_pages!!)
                    response.page?.plus(1) else null
            )
        } catch (e: IOException) {
            LoadResult.Error(throwable = e)
        } catch (e: HttpException) {
            LoadResult.Error(throwable = e)
        }
    }
}