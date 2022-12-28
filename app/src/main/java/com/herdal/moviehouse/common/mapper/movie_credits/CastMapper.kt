package com.herdal.moviehouse.common.mapper.movie_credits

import com.herdal.moviehouse.common.mapper.DtoMapper
import com.herdal.moviehouse.data.remote.model.movie_credits.CastDto
import com.herdal.moviehouse.domain.uimodel.movie_credits.CastUiModel

class CastMapper : DtoMapper<CastDto, CastUiModel> {
    override fun toDomain(response: CastDto): CastUiModel = CastUiModel(
        adult = response.adult,
        cast_id = response.cast_id,
        character = response.character,
        credit_id = response.credit_id,
        gender = response.gender,
        id = response.id,
        known_for_department = response.known_for_department,
        name = response.name,
        order = response.order,
        original_name = response.original_name,
        popularity = response.popularity,
        profile_path = response.profile_path
    )

    override fun fromDomain(domainModel: CastUiModel): CastDto = CastDto(
        adult = domainModel.adult,
        cast_id = domainModel.cast_id,
        character = domainModel.character,
        credit_id = domainModel.credit_id,
        gender = domainModel.gender,
        id = domainModel.id,
        known_for_department = domainModel.known_for_department,
        name = domainModel.name,
        order = domainModel.order,
        original_name = domainModel.original_name,
        popularity = domainModel.popularity,
        profile_path = domainModel.profile_path
    )

    override fun toDomainList(tList: List<CastDto>): List<CastUiModel> =
        tList.map { castDto ->
            toDomain(castDto)
        }

    override fun fromDomainList(domainList: List<CastUiModel>): List<CastDto> =
        domainList.map { castUiModel ->
            fromDomain(castUiModel)
        }
}