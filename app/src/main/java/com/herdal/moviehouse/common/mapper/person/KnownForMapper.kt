package com.herdal.moviehouse.common.mapper.person

import com.herdal.moviehouse.common.mapper.BaseMapper
import com.herdal.moviehouse.data.remote.model.person.KnownForDto
import com.herdal.moviehouse.domain.uimodel.person.KnowForUiModel

class KnownForMapper : BaseMapper<KnownForDto, KnowForUiModel> {
    override fun toDomain(response: KnownForDto): KnowForUiModel = KnowForUiModel(
        adult = response.adult,
        backdrop_path = response.backdrop_path,
        first_air_date = response.first_air_date,
        genre_ids = response.genre_ids,
        id = response.id,
        media_type = response.media_type,
        name = response.name,
        original_language = response.original_language,
        origin_country = response.origin_country,
        original_name = response.original_name,
        original_title = response.original_title,
        overview = response.overview,
        poster_path = response.poster_path,
        release_date = response.release_date,
        title = response.title,
        video = response.video,
        vote_average = response.vote_average,
        vote_count = response.vote_count
    )

    override fun fromDomain(domainModel: KnowForUiModel): KnownForDto = KnownForDto(
        adult = domainModel.adult,
        backdrop_path = domainModel.backdrop_path,
        first_air_date = domainModel.first_air_date,
        genre_ids = domainModel.genre_ids,
        id = domainModel.id,
        media_type = domainModel.media_type,
        name = domainModel.name,
        original_language = domainModel.original_language,
        origin_country = domainModel.origin_country,
        original_name = domainModel.original_name,
        original_title = domainModel.original_title,
        overview = domainModel.overview,
        poster_path = domainModel.poster_path,
        release_date = domainModel.release_date,
        title = domainModel.title,
        video = domainModel.video,
        vote_average = domainModel.vote_average,
        vote_count = domainModel.vote_count
    )

    override fun toDomainList(tList: List<KnownForDto>): List<KnowForUiModel> =
        tList.map { knownForDto ->
            toDomain(knownForDto)
        }

    override fun fromDomainList(domainList: List<KnowForUiModel>): List<KnownForDto> =
        domainList.map { knownForUiModel ->
            fromDomain(knownForUiModel)
        }
}