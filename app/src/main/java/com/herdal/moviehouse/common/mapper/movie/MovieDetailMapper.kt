package com.herdal.moviehouse.common.mapper.movie

import com.herdal.moviehouse.common.mapper.DtoMapper
import com.herdal.moviehouse.data.remote.model.movie_detail.MovieDetailDto
import com.herdal.moviehouse.domain.uimodel.movie_detail.MovieDetailUiModel

class MovieDetailMapper(
    private val genreMapper: GenreMapper,
    private val companyMapper: CompanyMapper
) : DtoMapper<MovieDetailDto, MovieDetailUiModel> {

    override fun toDomain(response: MovieDetailDto): MovieDetailUiModel {
        val genres = response.genres?.map { genreDto ->
            genreMapper.toDomain(genreDto)
        }
        val companies = response.production_companies.map { companyDto ->
            companyMapper.toDomain(companyDto)
        }
        return MovieDetailUiModel(
            adult = response.adult,
            backdrop_path = response.backdrop_path,
            budget = response.budget,
            genres = genres,
            homepage = response.homepage,
            id = response.id,
            imdb_id = response.imdb_id,
            original_title = response.original_title,
            original_language = response.original_language,
            overview = response.overview,
            popularity = response.popularity,
            poster_path = response.poster_path,
            production_companies = companies,
            release_date = response.release_date,
            revenue = response.revenue,
            runtime = response.runtime,
            status = response.status,
            tagline = response.tagline,
            title = response.title,
            vote_average = response.vote_average,
            vote_count = response.vote_count
        )
    }

    override fun fromDomain(domainModel: MovieDetailUiModel): MovieDetailDto {
        val genres = domainModel.genres?.map { genreUiModel ->
            genreMapper.fromDomain(genreUiModel)
        }
        val companies = domainModel.production_companies.map { companyUiModel ->
            companyMapper.fromDomain(companyUiModel)
        }

        return MovieDetailDto(
            adult = domainModel.adult,
            backdrop_path = domainModel.backdrop_path,
            budget = domainModel.budget,
            genres = genres,
            homepage = domainModel.homepage,
            id = domainModel.id,
            imdb_id = domainModel.imdb_id,
            original_title = domainModel.original_title,
            original_language = domainModel.original_language,
            overview = domainModel.overview,
            popularity = domainModel.popularity,
            poster_path = domainModel.poster_path,
            production_companies = companies,
            release_date = domainModel.release_date,
            revenue = domainModel.revenue,
            runtime = domainModel.runtime,
            status = domainModel.status,
            tagline = domainModel.tagline,
            title = domainModel.title,
            vote_average = domainModel.vote_average,
            vote_count = domainModel.vote_count
        )
    }

    override fun toDomainList(tList: List<MovieDetailDto>): List<MovieDetailUiModel> =
        tList.map { movieDetailDto ->
            toDomain(movieDetailDto)
        }

    override fun fromDomainList(domainList: List<MovieDetailUiModel>): List<MovieDetailDto> =
        domainList.map { movieDetailUiModel ->
            fromDomain(movieDetailUiModel)
        }
}