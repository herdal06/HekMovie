package com.herdal.moviehouse.common.mapper.person

import com.herdal.moviehouse.common.mapper.DtoMapper
import com.herdal.moviehouse.data.remote.model.person_detail.PersonDetailDto
import com.herdal.moviehouse.domain.uimodel.person.PersonDetailUiModel

class PersonDetailMapper : DtoMapper<PersonDetailDto, PersonDetailUiModel> {
    override fun toDomain(response: PersonDetailDto): PersonDetailUiModel = PersonDetailUiModel(
        adult = response.adult,
        also_known_as = response.also_known_as,
        biography = response.biography,
        birthday = response.birthday,
        deathday = response.deathday,
        gender = response.gender,
        homepage = response.homepage,
        id = response.id,
        imdb_id = response.imdb_id,
        known_for_department = response.known_for_department,
        name = response.name,
        place_of_birth = response.place_of_birth,
        popularity = response.popularity,
        profile_path = response.profile_path
    )

    override fun fromDomain(domainModel: PersonDetailUiModel): PersonDetailDto = PersonDetailDto(
        adult = domainModel.adult,
        also_known_as = domainModel.also_known_as,
        biography = domainModel.biography,
        birthday = domainModel.birthday,
        deathday = domainModel.deathday,
        gender = domainModel.gender,
        homepage = domainModel.homepage,
        id = domainModel.id,
        imdb_id = domainModel.imdb_id,
        known_for_department = domainModel.known_for_department,
        name = domainModel.name,
        place_of_birth = domainModel.place_of_birth,
        popularity = domainModel.popularity,
        profile_path = domainModel.profile_path
    )

    override fun toDomainList(tList: List<PersonDetailDto>): List<PersonDetailUiModel> =
        tList.map { personDetailDto ->
            toDomain(personDetailDto)
        }

    override fun fromDomainList(domainList: List<PersonDetailUiModel>): List<PersonDetailDto> =
        domainList.map { personDetailUiModel ->
            fromDomain(personDetailUiModel)
        }
}