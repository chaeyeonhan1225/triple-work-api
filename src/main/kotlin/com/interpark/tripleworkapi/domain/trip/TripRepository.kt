package com.interpark.tripleworkapi.domain.trip

import org.springframework.data.jpa.repository.JpaRepository

interface TripRepository: JpaRepository<Trip, Long> {
}