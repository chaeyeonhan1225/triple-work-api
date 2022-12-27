package com.interpark.tripleworkapi.domain.viewlog

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CityViewLogRepository: JpaRepository<CityViewLog, Long> {
}