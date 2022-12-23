package com.herdal.moviehouse.data.remote.service

import com.herdal.moviehouse.BuildConfig
import com.herdal.moviehouse.data.remote.model.person.PersonResponse
import com.herdal.moviehouse.data.remote.model.person_detail.PersonDetailDto
import com.herdal.moviehouse.utils.ApiConstants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PersonService {

    @GET(ApiConstants.Endpoints.POPULAR_PEOPLE)
    suspend fun getPopularPeople(
        @Query("page") page: Int,
        @Query("api_key") api_key: String = BuildConfig.API_KEY,
        @Query("language") language: String = ApiConstants.language
    ): PersonResponse

    @GET(ApiConstants.Endpoints.PERSON_DETAILS)
    suspend fun getPersonDetails(
        @Path("person_id") id: Int,
        @Query("api_key") api_key: String = BuildConfig.API_KEY,
        @Query("language") language: String = ApiConstants.language
    ): PersonDetailDto
}