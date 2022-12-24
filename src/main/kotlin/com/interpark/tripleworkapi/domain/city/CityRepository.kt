package com.interpark.tripleworkapi.domain.city

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface CityRepository: JpaRepository<City, CityId> {
    fun findAllByCreatedAtAfter(at: LocalDateTime): List<City>
}