package com.herdal.moviehouse.common.data_source

import com.herdal.moviehouse.data.remote.model.video.VideoResponse

interface VideoDataSource {
    interface Remote {
        suspend fun getVideos(movieId: Int): VideoResponse
    }
}