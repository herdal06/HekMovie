package com.herdal.moviehouse.data.repository

import com.herdal.moviehouse.common.data_source.VideoDataSource
import com.herdal.moviehouse.common.mapper.video.VideoMapper
import com.herdal.moviehouse.domain.repository.VideoRepository
import com.herdal.moviehouse.domain.uimodel.VideoUiModel
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
    private val videoMapper: VideoMapper,
    private val remote: VideoDataSource.Remote
) : VideoRepository {
    override suspend fun getVideos(movieId: Int): List<VideoUiModel> {
        return videoMapper.toDomainList(remote.getVideos(movieId = movieId).results)
    }
}