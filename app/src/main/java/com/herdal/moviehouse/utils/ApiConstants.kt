package com.herdal.moviehouse.utils

object ApiConstants {
    const val language = "en-US"
    const val BASE_URL = "https://api.themoviedb.org/"
    const val STARTING_PAGE = 1
    private const val BASE_POSTER_PATH =
        "https://image.tmdb.org/t/p/w342" // https://image.tmdb.org/t/p/w342/5hoS3nEkGGXUfmnu39yw1k52JX5.jpg
    private const val BASE_BACKDROP_PATH = "https://image.tmdb.org/t/p/w780"
    const val NETWORK_PAGE_SIZE = 10

    object Endpoints {
        const val GENRES = "3/genre/movie/list"
        const val POPULAR = "3/movie/popular"
        const val TOP_RATED = "3/movie/top_rated"
        const val UPCOMING = "3/movie/upcoming"
        const val NOW_PLAYING = "3/movie/now_playing"
        const val MOVIE_DETAILS = "3/movie/{id}"
        const val SIMILAR = "3/movie/{id}/similar"
        const val RECOMMENDED = "3/movie/{id}/recommendations"
        const val REVIEWS = "3/movie/{movie_id}/reviews"
        const val POPULAR_PEOPLE = "3/person/popular"
        const val PERSON_DETAILS = "3/person/{person_id}"
        const val MOVIE_CREDITS = "3/movie/{movie_id}/credits"
        const val MOVIES_BY_GENRE = "3/discover/movie"
        const val SEARCH_MOVIE = "3/search/movie"
        const val SEARCH_TV_SERIES = "3/search/tv"
        const val SEARCH_PEOPLE = "3/search/person"
    }

    @JvmStatic
    fun getPosterPath(posterPath: String?): String {
        return BASE_POSTER_PATH + posterPath
    }

    @JvmStatic
    fun getBackdropPath(backdropPath: String?): String {
        return BASE_BACKDROP_PATH + backdropPath
    }
}