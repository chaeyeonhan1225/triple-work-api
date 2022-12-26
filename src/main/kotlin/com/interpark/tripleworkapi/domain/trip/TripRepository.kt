package com.interpark.tripleworkapi.domain.trip

import com.interpark.tripleworkapi.domain.city.CityId
import com.interpark.tripleworkapi.domain.user.UserId
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDate

interface TripRepository : JpaRepository<Trip, TripId>, JpaSpecificationExecutor<Trip> {
    fun findAllByCityId(@Param("cityId") cityId: CityId): List<Trip>

    @Query(
        "select t from Trip as t where t.userId = :userId and t.cityId not in :excludedCityIds and" +
                " t.plan.startedAt > current_date and t.plan.startedAt >= :at order by t.plan.startedAt"
    )
    fun findImpendingTrips(
        @Param("userId") userId: UserId,
        @Param("excludedCityIds") excludedCityIds: List<CityId>,
        @Param("at") at: LocalDate,
        pageable: Pageable
    ): List<Trip>
}