package com.herdal.moviehouse.data.remote.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.herdal.moviehouse.data.remote.model.movies.MovieDto
import com.herdal.moviehouse.data.remote.service.MovieService
import okio.IOException
import retrofit2.HttpException

class SearchMoviePagingSource(
    private val movieService: MovieService,
    private val searchQuery: String
) : PagingSource<Int, MovieDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDto> {
        val pageIndex = params.key ?: 1
        return try {
            val response = movieService.searchMovies(page = pageIndex, query = searchQuery)
            val games = response.results
            val nextKey =
                if (games.isEmpty()) {
                    null
                } else {
                    // By default, initial load size = 3 * NETWORK PAGE SIZE
                    // ensure we're not requesting duplicating items at the 2nd request
                    pageIndex + (params.loadSize / 20)
                }
            LoadResult.Page(
                data = games,
                prevKey = if (pageIndex == 1) null else pageIndex,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    /**
     * The refresh key is used for subsequent calls to PagingSource.Load after the initial load.
     */
    override fun getRefreshKey(state: PagingState<Int, MovieDto>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index.
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}