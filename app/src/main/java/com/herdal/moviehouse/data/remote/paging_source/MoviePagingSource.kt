package com.herdal.moviehouse.data.remote.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.herdal.moviehouse.common.enums.MovieListType
import com.herdal.moviehouse.data.remote.model.movies.MovieDto
import com.herdal.moviehouse.data.remote.service.MovieService
import com.herdal.moviehouse.utils.ApiConstants.STARTING_PAGE
import okio.IOException
import retrofit2.HttpException

class MoviePagingSource(
    private val movieService: MovieService,
    private val movieListType: MovieListType,
    private val genreId: Int? = null,
    private val movieId: Int? = null
) : PagingSource<Int, MovieDto>() {
    override fun getRefreshKey(state: PagingState<Int, MovieDto>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDto> {
        val nextPage = params.key ?: STARTING_PAGE

        return try {
            val response = when (movieListType) {
                MovieListType.POPULAR -> {
                    movieService.getPopularMovies(page = nextPage)
                }
                MovieListType.NOW_PLAYING -> {
                    movieService.getNowPlayingMovies(
                        page = nextPage
                    )
                }
                MovieListType.TOP_RATED -> {
                    movieService.getTopRatedMovies(
                        page = nextPage
                    )
                }
                MovieListType.UPCOMING -> {
                    movieService.getUpcomingMovies(
                        page = nextPage
                    )
                }
                MovieListType.RECOMMENDED -> {
                    movieService.getRecommendedMovies(
                        id = movieId!!,
                        page = nextPage
                    )
                }
                MovieListType.SIMILAR -> {
                    movieService.getSimilarMovies(
                        id = movieId!!,
                        page = nextPage
                    )
                }
                MovieListType.BY_GENRE -> {
                    movieService.getMoviesByGenre(
                        genreId = genreId!!,
                        page = nextPage
                    )
                }
                else -> {
                    throw IllegalArgumentException("Invalid movie list type: $movieListType")
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