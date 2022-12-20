package com.herdal.moviehouse.data.remote.service

import com.herdal.moviehouse.BuildConfig.API_KEY
import com.herdal.moviehouse.data.remote.model.movie_detail.MovieDetailDto
import com.herdal.moviehouse.data.remote.model.movies.MovieResponse
import com.herdal.moviehouse.utils.ApiConstants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET(ApiConstants.Endpoints.POPULAR)
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = ApiConstants.language
    ): MovieResponse

    @GET(ApiConstants.Endpoints.TOP_RATED)
    suspend fun getTopRatedMovies(
        @Query("page") page: Int,
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = ApiConstants.language
    ): MovieResponse

    @GET(ApiConstants.Endpoints.UPCOMING)
    suspend fun getUpcomingMovies(
        @Query("page") page: Int,
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = ApiConstants.language
    ): MovieResponse

    @GET(ApiConstants.Endpoints.NOW_PLAYING)
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int,
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = ApiConstants.language
    ): MovieResponse

    @GET(ApiConstants.Endpoints.MOVIE_DETAILS)
    suspend fun getMovieDetails(
        @Path("id") id: Int,
        @Query("api_key") api_key: String = API_KEY
    ): MovieDetailDto
}