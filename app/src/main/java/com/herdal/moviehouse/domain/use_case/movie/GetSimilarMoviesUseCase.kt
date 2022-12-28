package com.herdal.moviehouse.domain.use_case.movie

import androidx.paging.PagingData
import com.herdal.moviehouse.domain.repository.MovieRepository
import com.herdal.moviehouse.domain.uimodel.movie.MovieUiModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSimilarMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(movieId: Int): Flow<PagingData<MovieUiModel>> {
        return movieRepository.getSimilarMovies(movieId)
    }
}