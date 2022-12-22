package com.herdal.moviehouse.data.remote.service

import com.herdal.moviehouse.BuildConfig
import com.herdal.moviehouse.data.remote.model.review.ReviewResponse
import com.herdal.moviehouse.utils.ApiConstants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReviewService {

    @GET(ApiConstants.Endpoints.REVIEWS)
    suspend fun getReviewsForMovie(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int,
        @Query("api_key") api_key: String = BuildConfig.API_KEY
    ): ReviewResponse
}