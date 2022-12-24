package com.interpark.tripleworkapi.application

import com.interpark.tripleworkapi.domain.city.CityRepository
import com.interpark.tripleworkapi.domain.trip.TripId
import com.interpark.tripleworkapi.domain.trip.TripRepository
import com.interpark.tripleworkapi.domain.trip.TripView
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class TripProvider(
    private val repository: TripRepository,
    private val cityRepository: CityRepository
) {
    fun findById(id: Long): TripView {
        val trip = repository.findById(TripId(id)).orElseThrow { NotFoundException() }
        // val cityIds = trip.citiesToTrip.map { it.cityId }
        val city = cityRepository.findById(trip.cityId).orElseThrow { NotFoundException() }

        return TripView(trip = trip, city = city)
    }

}