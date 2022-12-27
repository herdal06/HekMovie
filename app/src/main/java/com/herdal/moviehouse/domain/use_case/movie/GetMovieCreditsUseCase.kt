package com.herdal.moviehouse.domain.use_case.movie

import com.herdal.moviehouse.common.Resource
import com.herdal.moviehouse.data.remote.model.movie_credits.MovieCreditsResponse
import com.herdal.moviehouse.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetMovieCreditsUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(movieId: Int): Flow<Resource<MovieCreditsResponse>> = flow {
        try {
            emit(Resource.Loading())
            val movieCreditsResponse = movieRepository.getMovieCredits(movieId)
            emit(Resource.Success(data = movieCreditsResponse))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.message))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage))
        }
    }
}