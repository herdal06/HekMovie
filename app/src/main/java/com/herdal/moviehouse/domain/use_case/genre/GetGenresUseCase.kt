package com.herdal.moviehouse.domain.use_case.genre

import com.herdal.moviehouse.common.Resource
import com.herdal.moviehouse.domain.repository.GenreRepository
import com.herdal.moviehouse.domain.uimodel.genre.GenreUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(
    private val genreRepository: GenreRepository
) {
    operator fun invoke(): Flow<Resource<List<GenreUiModel>>> = flow {
        try {
            emit(Resource.Loading())
            val genres = genreRepository.getGenres()
            Timber.d("$genres")
            emit(Resource.Success(data = genres))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.message))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.message))
        }
    }
}