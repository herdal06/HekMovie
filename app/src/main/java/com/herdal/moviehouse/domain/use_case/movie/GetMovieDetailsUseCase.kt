package com.herdal.moviehouse.domain.use_case.movie

import com.herdal.moviehouse.common.Resource
import com.herdal.moviehouse.domain.repository.MovieRepository
import com.herdal.moviehouse.domain.uimodel.MovieDetailUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(id: Int): Flow<Resource<MovieDetailUiModel>> = flow {
        try {
            emit(Resource.Loading())
            val movieDetail = movieRepository.getMovieDetails(id)
            emit(Resource.Success(data = movieDetail))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.message))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage))
        }
    }
}