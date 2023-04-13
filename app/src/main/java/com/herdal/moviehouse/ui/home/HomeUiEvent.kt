package com.herdal.moviehouse.ui.home

sealed class HomeUiEvent {
    object GetGenres : HomeUiEvent()
    object GetPopularMovies : HomeUiEvent()
    object GetTopRatedMovies : HomeUiEvent()
    object GetUpcomingMovies : HomeUiEvent()
    object GetNowPlayingMovies : HomeUiEvent()
    object GetPopularPeople : HomeUiEvent()
}