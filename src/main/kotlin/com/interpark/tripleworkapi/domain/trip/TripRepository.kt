package com.interpark.tripleworkapi.domain.trip

import com.interpark.tripleworkapi.domain.city.City
import com.interpark.tripleworkapi.domain.city.CityId
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query

interface TripRepository : JpaRepository<Trip, TripId>, JpaSpecificationExecutor<Trip> {
    @Query(
        "select t from Trip as t where t.plan.startedAt <= current_date and t.plan.endedAt >= current_date and t.status > 0 "
    )
    fun findOngoingTrips(): List<Trip>

    @Query(
        "select t from Trip as t where t.plan.startedAt <= current_date and t.status > 0 order by t.plan.startedAt desc"
    )
    fun findImpendingTrips(pageable: Pageable): List<Trip>

    fun findAllByCityId(cityId: CityId): List<Trip>
}