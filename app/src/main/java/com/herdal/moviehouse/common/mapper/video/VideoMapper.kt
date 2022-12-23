package com.herdal.moviehouse.common.mapper.video

import com.herdal.moviehouse.common.mapper.BaseMapper
import com.herdal.moviehouse.data.remote.model.video.VideoDto
import com.herdal.moviehouse.domain.uimodel.VideoUiModel

class VideoMapper : BaseMapper<VideoDto, VideoUiModel> {
    override fun toDomain(response: VideoDto): VideoUiModel = VideoUiModel(
        id = response.id,
        iso_639_1 = response.iso_639_1,
        iso_3166_1 = response.iso_3166_1,
        key = response.key,
        name = response.name,
        official = response.official,
        published_at = response.published_at,
        site = response.site,
        size = response.size,
        type = response.type
    )

    override fun fromDomain(domainModel: VideoUiModel): VideoDto = VideoDto(
        id = domainModel.id,
        iso_639_1 = domainModel.iso_639_1,
        iso_3166_1 = domainModel.iso_3166_1,
        key = domainModel.key,
        name = domainModel.name,
        official = domainModel.official,
        published_at = domainModel.published_at,
        site = domainModel.site,
        size = domainModel.size,
        type = domainModel.type
    )

    override fun toDomainList(tList: List<VideoDto>): List<VideoUiModel> =
        tList.map { videDto ->
            toDomain(videDto)
        }

    override fun fromDomainList(domainList: List<VideoUiModel>): List<VideoDto> =
        domainList.map { videoUiModel ->
            fromDomain(videoUiModel)
        }
}