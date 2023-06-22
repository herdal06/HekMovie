package com.herdal.moviehouse.domain.use_case.person

import com.bumptech.glide.load.HttpException
import com.herdal.moviehouse.common.Resource
import com.herdal.moviehouse.domain.repository.PersonRepository
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class SearchPeopleUseCase @Inject constructor(
    private val personRepository: PersonRepository,
) {
    operator fun invoke(query: String) = flow {
        try {
            emit(Resource.Loading())
            val people = personRepository.searchPeople(query = query)
            emit(Resource.Success(data = people))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.message))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.message))
        }
    }
}