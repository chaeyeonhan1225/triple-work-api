package com.interpark.tripleworkapi.domain.city

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CityRepository: JpaRepository<City, CityId> {
}