package com.interpark.tripleworkapi.domain.trip

import com.interpark.tripleworkapi.domain.city.City
import com.interpark.tripleworkapi.domain.city.CityId
import com.interpark.tripleworkapi.domain.user.UserId
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query

interface TripRepository : JpaRepository<Trip, TripId>, JpaSpecificationExecutor<Trip> {
    @Query(
        "select t.cityId from Trip as t where t.plan.startedAt <= current_date and t.plan.endedAt >= current_date and t.userId = :userId and t.status > 0 order by t.plan.startedAt asc"
    )
    fun findOngoingTrips(userId: UserId): List<TripCityId>

    @Query(
        "select distinct(t.cityId) from Trip as t where t.plan.startedAt <= current_date and t.userId = :userId and t.cityId not in :excludedCityIds and t.status > 0 order by t.plan.startedAt desc"
    )
    fun findImpendingTrips(userId: UserId, excludedCityIds: List<CityId>, pageable: Pageable): List<Trip>

    fun findAllByCityId(cityId: CityId): List<Trip>
}

data class TripCityId (
    val cityId: CityId
)