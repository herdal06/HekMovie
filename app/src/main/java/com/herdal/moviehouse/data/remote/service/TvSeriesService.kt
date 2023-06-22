package com.herdal.moviehouse.data.remote.service

import com.herdal.moviehouse.BuildConfig
import com.herdal.moviehouse.data.remote.model.tv_series.TvSeriesResponse
import com.herdal.moviehouse.utils.ApiConstants
import retrofit2.http.GET
import retrofit2.http.Query

interface TvSeriesService {

    @GET(ApiConstants.Endpoints.SEARCH_TV_SERIES)
    suspend fun searchTvSeries(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("api_key") api_key: String = BuildConfig.API_KEY,
        @Query("include_adult") includeAdult: Boolean = true,
    ): TvSeriesResponse
}