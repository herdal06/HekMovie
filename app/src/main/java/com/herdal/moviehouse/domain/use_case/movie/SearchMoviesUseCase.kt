package com.herdal.moviehouse.domain.use_case.movie

import com.bumptech.glide.load.HttpException
import com.herdal.moviehouse.common.Resource
import com.herdal.moviehouse.domain.repository.MovieRepository
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    operator fun invoke(query: String) = flow {
        try {
            emit(Resource.Loading())
            val movies = movieRepository.searchMovies(query = query)
            emit(Resource.Success(data = movies))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.message))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.message))
        }
    }
}