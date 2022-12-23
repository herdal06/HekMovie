package com.herdal.moviehouse.data.remote.data_source

import com.herdal.moviehouse.common.data_source.VideoDataSource
import com.herdal.moviehouse.data.remote.model.video.VideoResponse
import com.herdal.moviehouse.data.remote.service.VideoService
import javax.inject.Inject

class VideoRemoteDataSource @Inject constructor(
    private val videoService: VideoService
) : VideoDataSource.Remote {
    override suspend fun getVideos(movieId: Int): VideoResponse =
        videoService.getVideos(movieId)
}