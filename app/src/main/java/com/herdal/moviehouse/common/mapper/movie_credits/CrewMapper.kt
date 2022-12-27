package com.herdal.moviehouse.common.mapper.movie_credits

import com.herdal.moviehouse.common.mapper.BaseMapper
import com.herdal.moviehouse.data.remote.model.movie_credits.CrewDto
import com.herdal.moviehouse.domain.uimodel.movie_credits.CrewUiModel

class CrewMapper : BaseMapper<CrewDto, CrewUiModel> {
    override fun toDomain(response: CrewDto): CrewUiModel = CrewUiModel(
        adult = response.adult,
        credit_id = response.credit_id,
        gender = response.gender,
        id = response.id,
        known_for_department = response.known_for_department,
        name = response.name,
        original_name = response.original_name,
        popularity = response.popularity,
        profile_path = response.profile_path,
        department = response.department,
        job = response.job
    )

    override fun fromDomain(domainModel: CrewUiModel): CrewDto = CrewDto(
        adult = domainModel.adult,
        credit_id = domainModel.credit_id,
        gender = domainModel.gender,
        id = domainModel.id,
        known_for_department = domainModel.known_for_department,
        name = domainModel.name,
        original_name = domainModel.original_name,
        popularity = domainModel.popularity,
        profile_path = domainModel.profile_path,
        department = domainModel.department,
        job = domainModel.job
    )

    override fun toDomainList(tList: List<CrewDto>): List<CrewUiModel> =
        tList.map { crewDto ->
            toDomain(crewDto)
        }

    override fun fromDomainList(domainList: List<CrewUiModel>): List<CrewDto> =
        domainList.map { crewUiModel ->
            fromDomain(crewUiModel)
        }
}