package com.herdal.moviehouse.domain.repository

import com.herdal.moviehouse.domain.uimodel.VideoUiModel

interface VideoRepository {
    suspend fun getVideos(movieId: Int): List<VideoUiModel>
}