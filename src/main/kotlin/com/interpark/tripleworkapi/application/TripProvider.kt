package com.interpark.tripleworkapi.application

import com.interpark.tripleworkapi.domain.city.CityRepository
import com.interpark.tripleworkapi.domain.exception.NotFoundException
import com.interpark.tripleworkapi.domain.trip.TripId
import com.interpark.tripleworkapi.domain.trip.TripRepository
import com.interpark.tripleworkapi.domain.trip.TripView
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class TripProvider(
    private val repository: TripRepository,
    private val cityRepository: CityRepository
) {
    fun findById(id: Long): TripView {
        val trip = repository.findById(TripId(id)).orElseThrow {
            NotFoundException(
                message = "존재하지 않는 여행입니다."
            )
        }

        val city = cityRepository.findById(trip.cityId).orElseThrow { NotFoundException(message = "존재하지 않는 도시입니다.") }

        return TripView(trip = trip, city = city)
    }

}