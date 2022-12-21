package com.interpark.tripleworkapi.application

import com.interpark.tripleworkapi.domain.trip.TripRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class TripProvider(
    private val repository: TripRepository
) {
    fun findById() {

    }

}