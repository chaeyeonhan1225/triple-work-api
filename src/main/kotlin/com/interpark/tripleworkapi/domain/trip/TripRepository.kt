package com.interpark.tripleworkapi.domain.trip

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface TripRepository: JpaRepository<Trip, Long>, JpaSpecificationExecutor<Trip>