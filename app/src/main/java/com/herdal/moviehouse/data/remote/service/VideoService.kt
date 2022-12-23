package com.herdal.moviehouse.data.remote.service

import com.herdal.moviehouse.BuildConfig
import com.herdal.moviehouse.data.remote.model.video.VideoResponse
import com.herdal.moviehouse.utils.ApiConstants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface VideoService {
    @GET(ApiConstants.Endpoints.VIDEOS)
    suspend fun getVideos(
        @Path("movie_id") movieId: Int,
        @Query("api_key") api_key: String = BuildConfig.API_KEY,
        @Query("language") language: String = ApiConstants.language
    ): VideoResponse
}