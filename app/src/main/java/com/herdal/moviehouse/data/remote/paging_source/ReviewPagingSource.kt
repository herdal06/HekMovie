package com.herdal.moviehouse.data.remote.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.herdal.moviehouse.data.remote.model.review.ReviewDto
import com.herdal.moviehouse.data.remote.service.ReviewService
import com.herdal.moviehouse.utils.ApiConstants
import okio.IOException
import retrofit2.HttpException

class ReviewPagingSource(
    private val reviewService: ReviewService,
    private val movieId: Int
) : PagingSource<Int, ReviewDto>() {
    override fun getRefreshKey(state: PagingState<Int, ReviewDto>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReviewDto> {
        val nextPage = params.key ?: ApiConstants.STARTING_PAGE

        return try {
            val response =
                reviewService.getReviewsForMovie(
                    movieId = movieId,
                    page = nextPage
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