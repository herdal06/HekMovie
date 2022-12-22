package com.herdal.moviehouse.common.mapper

interface BaseMapper<Response, DomainModel> {
    fun toDomain(response: Response): DomainModel
    fun fromDomain(domainModel: DomainModel): Response
    fun toDomainList(tList: List<Response>): List<DomainModel>
    fun fromDomainList(domainList: List<DomainModel>): List<Response>
}