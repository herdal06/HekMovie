package com.herdal.moviehouse.common.mapper.person

import com.herdal.moviehouse.common.mapper.DtoMapper
import com.herdal.moviehouse.data.remote.model.person.PersonDto
import com.herdal.moviehouse.domain.uimodel.person.PersonUiModel

class PersonMapper(
    private val knownForMapper: KnownForMapper
) : DtoMapper<PersonDto, PersonUiModel> {
    override fun toDomain(response: PersonDto): PersonUiModel {

        val knownFor = response.known_for.map { knownForDto ->
            knownForMapper.toDomain(knownForDto)
        }

        return PersonUiModel(
            gender = response.gender,
            id = response.id,
            known_for = knownFor,
            known_for_department = response.known_for_department,
            name = response.name,
            popularity = response.popularity,
            profile_path = response.profile_path
        )
    }

    override fun fromDomain(domainModel: PersonUiModel): PersonDto {
        val knownFor = domainModel.known_for.map { knownForUiModel ->
            knownForMapper.fromDomain(knownForUiModel)
        }

        return PersonDto(
            gender = domainModel.gender,
            id = domainModel.id,
            known_for = knownFor,
            known_for_department = domainModel.known_for_department,
            name = domainModel.name,
            popularity = domainModel.popularity,
            profile_path = domainModel.profile_path
        )
    }

    override fun toDomainList(tList: List<PersonDto>): List<PersonUiModel> =
        tList.map { personDto ->
            toDomain(personDto)
        }

    override fun fromDomainList(domainList: List<PersonUiModel>): List<PersonDto> =
        domainList.map { personUimodel ->
            fromDomain(personUimodel)
        }
}