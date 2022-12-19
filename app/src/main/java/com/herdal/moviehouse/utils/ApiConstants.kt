package com.herdal.moviehouse.utils

object ApiConstants {
    const val language = "en-US"
    const val BASE_URL = "https://api.themoviedb.org/"
    const val STARTING_PAGE = 1

    object Endpoints {
        const val GENRES = "3/genre/movie/list"
        const val POPULAR = "3/movie/popular"
        const val TOP_RATED = "3/movie/top_rated"
        const val UPCOMING = "3/movie/upcoming"
        const val NOW_PLAYING = "3/movie/latest"
    }
}