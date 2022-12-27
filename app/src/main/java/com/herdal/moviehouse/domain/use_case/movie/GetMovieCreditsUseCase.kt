package com.herdal.moviehouse.domain.use_case.movie

import com.herdal.moviehouse.common.Resource
import com.herdal.moviehouse.common.mapper.movie_credits.MovieCreditsMapper
import com.herdal.moviehouse.domain.repository.MovieRepository
import com.herdal.moviehouse.domain.uimodel.movie_credits.MovieCreditsUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetMovieCreditsUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieCreditsMapper: MovieCreditsMapper
) {
    operator fun invoke(movieId: Int): Flow<Resource<MovieCreditsUiModel>> = flow {
        try {
            emit(Resource.Loading())
            val movieCreditsResponse = movieRepository.getMovieCredits(movieId)
            emit(Resource.Success(data = movieCreditsMapper.toDomain(movieCreditsResponse)))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.message))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage))
        }
    }
}