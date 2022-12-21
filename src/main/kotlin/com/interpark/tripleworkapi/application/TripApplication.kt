package com.interpark.tripleworkapi.application

import com.interpark.tripleworkapi.domain.Trip
import com.interpark.tripleworkapi.domain.TripRepository
import com.interpark.tripleworkapi.domain.param.TripParam
import org.springframework.stereotype.Service

@Service
class TripApplication(
    private val repository: TripRepository
) {
    fun create(param: TripParam): Trip {
        val trip = Trip(param = param)
        return repository.save(trip)
    }
}