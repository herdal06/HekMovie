package com.herdal.moviehouse.data.remote.service

import com.herdal.moviehouse.BuildConfig.API_KEY
import com.herdal.moviehouse.data.remote.model.genre.GenreResponse
import com.herdal.moviehouse.utils.ApiConstants
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreService {
    @GET(ApiConstants.Endpoints.GENRES)
    suspend fun getAll(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = ApiConstants.language
    ): GenreResponse
}