package com.interpark.tripleworkapi.domain.viewlog

import com.interpark.tripleworkapi.domain.city.CityId
import com.interpark.tripleworkapi.domain.user.UserId
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface CityViewLogRepository: JpaRepository<CityViewLog, Long> {
    @Query(
        "select vl from CityViewLog as vl where " +
                "vl.userId = :userId and " +
                "vl.cityId not in :excludedCityIds and " +
                "vl.createdAt >= :at order by vl.id desc "
    )
    fun findCityViewLogRecentlyCreatedByUserId(
        @Param("userId") userId: UserId,
        @Param("excludedCityIds") excludedCityIds: List<CityId>,
        @Param("at") at: LocalDateTime,
        pageable: Pageable
    ): List<CityViewLog>
}