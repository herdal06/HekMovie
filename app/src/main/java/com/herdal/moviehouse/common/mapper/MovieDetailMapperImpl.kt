package com.herdal.moviehouse.common.mapper

import com.herdal.moviehouse.data.remote.model.movie_detail.MovieDetailDto
import com.herdal.moviehouse.domain.uimodel.MovieDetailUiModel

class MovieDetailMapperImpl(
    private val genreMapper: GenreMapper,
    private val companyMapper: CompanyMapper
) : MovieDetailMapper {

    override fun toDomain(t: MovieDetailDto): MovieDetailUiModel {
        val genres = t.genres.map { genreDto ->
            genreMapper.toDomain(genreDto)
        }
        val companies = t.production_companies.map { companyDto ->
            companyMapper.toDomain(companyDto)
        }
        return MovieDetailUiModel(
            adult = t.adult,
            backdrop_path = t.backdrop_path,
            budget = t.budget,
            genres = genres,
            homepage = t.homepage,
            id = t.id,
            imdb_id = t.imdb_id,
            original_title = t.original_title,
            original_language = t.original_language,
            overview = t.overview,
            popularity = t.popularity,
            poster_path = t.poster_path,
            production_companies = companies,
            release_date = t.release_date,
            revenue = t.revenue,
            runtime = t.runtime,
            status = t.status,
            tagline = t.tagline,
            title = t.title,
            vote_average = t.vote_average,
            vote_count = t.vote_count
        )
    }

    override fun fromDomain(domainModel: MovieDetailUiModel): MovieDetailDto {
        val genres = domainModel.genres.map { genreUiModel ->
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