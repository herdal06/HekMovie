package com.herdal.moviehouse.domain.use_case.tv_series

import com.bumptech.glide.load.HttpException
import com.herdal.moviehouse.common.Resource
import com.herdal.moviehouse.domain.repository.TvSeriesRepository
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class SearchTvSeriesUseCase @Inject constructor(
    private val tvSeriesRepository: TvSeriesRepository
) {
    operator fun invoke(query: String) = flow {
        try {
            emit(Resource.Loading())
            val tvSeries = tvSeriesRepository.searchPeople(query = query)
            emit(Resource.Success(data = tvSeries))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.message))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.message))
        }
    }
}