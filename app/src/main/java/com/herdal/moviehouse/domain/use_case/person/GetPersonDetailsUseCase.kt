package com.herdal.moviehouse.domain.use_case.person

import com.herdal.moviehouse.common.Resource
import com.herdal.moviehouse.domain.repository.PersonRepository
import com.herdal.moviehouse.domain.uimodel.person.PersonDetailUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetPersonDetailsUseCase @Inject constructor(
    private val personRepository: PersonRepository
) {
    operator fun invoke(id: Int): Flow<Resource<PersonDetailUiModel>> = flow {
        try {
            emit(Resource.Loading())
            val movieDetail = personRepository.getPersonDetails(id)
            emit(Resource.Success(data = movieDetail))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.message))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage))
        }
    }
}