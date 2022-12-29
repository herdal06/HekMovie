package com.herdal.moviehouse.data.remote.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.herdal.moviehouse.data.remote.model.person.PersonDto
import com.herdal.moviehouse.data.remote.service.PersonService
import com.herdal.moviehouse.utils.ApiConstants
import okio.IOException
import retrofit2.HttpException

class PersonPagingSource(
    private val personService: PersonService,
) : PagingSource<Int, PersonDto>() {
    override fun getRefreshKey(state: PagingState<Int, PersonDto>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PersonDto> {
        val nextPage = params.key ?: ApiConstants.STARTING_PAGE

        return try {
            val response = personService.getPopularPeople(
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