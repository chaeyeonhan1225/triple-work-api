package com.interpark.tripleworkapi.domain

import org.springframework.data.jpa.repository.JpaRepository

interface TripRepository: JpaRepository<Trip, Long> {
}