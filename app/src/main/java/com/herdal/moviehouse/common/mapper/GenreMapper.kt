package com.herdal.moviehouse.common.mapper

interface GenreMapper<T, DomainModel> {
    fun toDomain(t: T): DomainModel
    fun fromDomain(domainModel: DomainModel): T
    fun toDomainList(tList: List<T>): List<DomainModel>
    fun fromDomainList(domainList: List<DomainModel>): List<T>
}