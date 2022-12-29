package com.herdal.moviehouse.data.remote.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.herdal.moviehouse.common.enums.MovieDetailEnum
import com.herdal.moviehouse.data.remote.model.movies.MovieDto
import com.herdal.moviehouse.data.remote.service.MovieService
import com.herdal.moviehouse.utils.ApiConstants
import okio.IOException
import retrofit2.HttpException

class MovieDetailPagingSource(
    private val movieService: MovieService,
    private val movieDetailEnum: MovieDetailEnum,
    private val movieId: Int
) : PagingSource<Int, MovieDto>() {
    override fun getRefreshKey(state: PagingState<Int, MovieDto>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDto> {
        val nextPage = params.key ?: ApiConstants.STARTING_PAGE

        return try {
            val response = when (movieDetailEnum) {
                MovieDetailEnum.SIMILAR -> {
                    movieService.getSimilarMovies(
                        id = movieId,
                        page = nextPage
                    )
                }
                MovieDetailEnum.RECOMMENDED -> {
                    movieService.getRecommendedMovies(
                        id = movieId,
                        page = nextPage
                    )
                }
            }
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