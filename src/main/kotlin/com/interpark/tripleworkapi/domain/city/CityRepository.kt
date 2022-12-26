package com.interpark.tripleworkapi.domain.city

import com.interpark.tripleworkapi.domain.user.UserId
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.time.LocalDateTime

@Repository
interface CityRepository: JpaRepository<City, CityId> {
    @Query("select c from City as c where c.createdAt >= :at and c.id not in (:excludedCityIds) and c.status > 0 order by c.createdAt desc")
    fun findAllByCreatedAtAfter(@Param("at") at: LocalDateTime, @Param("excludedCityIds") excludedCityIds: List<CityId>, pageable: Pageable): List<City>

    @Query(value = "select c from City as c where c.id not in (:excludedCityIds)  order by random()")
    fun findAllByRandom(@Param("excludedCityIds") excludedCityIds: List<CityId>, pageable: Pageable): List<City>

    @Query(
        "select c from City as c join Trip as t ON c.id = t.cityId where" +
                " t.userId = :userId and t.status > 0 and c.status > 0 and " +
                "t.plan.startedAt <= current_date and t.plan.endedAt >= current_date order by t.plan.startedAt asc"
    )
    fun findCitiesOfOngoingTrip(@Param("userId") userId: UserId): List<City>

    @Query(
        "select distinct (c), t.plan.startedAt from City as c join Trip as t ON c.id = t.cityId where" +
                " t.userId = :userId and t.status > 0 and c.status > 0 and" +
                " t.plan.startedAt > current_date and t.plan.startedAt >= :at and" +
                " c.id not in :excludedCityIds order by t.plan.startedAt"
    )
    fun findCitiesOfImpendingTrip(@Param("userId") userId: UserId, @Param("excludedCityIds") excludedCityIds: List<CityId>, @Param("at") at: LocalDate, pageable: Pageable): List<City>

    @Query(
        "select distinct (c), vl.id from City as c join CityViewLog as vl ON c.id = vl.cityId where" +
                " vl.userId = :userId and c.status > 0 and" +
                " vl.createdAt >= :at and" +
                " c.id not in :excludedCityIds order by vl.id"
    )
    fun findCitiesOfRecentlyViewed(@Param("userId") userId: UserId, @Param("excludedCityIds") excludedCityIds: List<CityId>, @Param("at") at: LocalDateTime, pageable: Pageable): List<City>

}