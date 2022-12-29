package com.herdal.moviehouse.common.mapper.movie_credits

import com.herdal.moviehouse.common.mapper.DtoMapper
import com.herdal.moviehouse.data.remote.model.movie_credits.MovieCreditsResponse
import com.herdal.moviehouse.domain.uimodel.movie_credits.MovieCreditsUiModel

class MovieCreditsMapper(
    private val castMapper: CastMapper,
    private val crewMapper: CrewMapper
) : DtoMapper<MovieCreditsResponse, MovieCreditsUiModel> {
    override fun toDomain(response: MovieCreditsResponse): MovieCreditsUiModel {
        val castsUiModel = response.cast.map { castDto ->
            castMapper.toDomain(castDto)
        }
        val crewsUiModel = response.crew.map { crewDto ->
            crewMapper.toDomain(crewDto)
        }
        return MovieCreditsUiModel(
            id = response.id,
            cast = castsUiModel,
            crew = crewsUiModel
        )
    }

    override fun fromDomain(domainModel: MovieCreditsUiModel): MovieCreditsResponse {
        val castsResponse = domainModel.cast.map { castUiModel ->
            castMapper.fromDomain(castUiModel)
        }
        val crewsResponse = domainModel.crew.map { crewUiModel ->
            crewMapper.fromDomain(crewUiModel)
        }
        return MovieCreditsResponse(
            id = domainModel.id,
            cast = castsResponse,
            crew = crewsResponse
        )
    }

    override fun toDomainList(tList: List<MovieCreditsResponse>): List<MovieCreditsUiModel> =
        tList.map { creditsResponse ->
            toDomain(creditsResponse)
        }

    override fun fromDomainList(domainList: List<MovieCreditsUiModel>): List<MovieCreditsResponse> =
        domainList.map { creditsUiModel ->
            fromDomain(creditsUiModel)
        }
}