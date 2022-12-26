package com.interpark.tripleworkapi.domain.service

import com.interpark.tripleworkapi.domain.city.CityId
import com.interpark.tripleworkapi.domain.city.CityRepository
import com.interpark.tripleworkapi.domain.exception.CityUnableDeleteException
import com.interpark.tripleworkapi.domain.exception.NotFoundException
import com.interpark.tripleworkapi.domain.trip.TripRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Component
class CityDeleteService(
    private val tripRepository: TripRepository,
    private val cityRepository: CityRepository
) {
    fun delete(cityId: CityId) {
        val trips = tripRepository.findAllByCityId(cityId)
        if (trips.isNotEmpty()) {
            throw CityUnableDeleteException(message = "해당 도시는 삭제할 수 없습니다.")
        }

        val city = cityRepository.findById(cityId).orElseThrow {
            NotFoundException(message = "존재하지 않는 도시입니다.")
        }
        city.delete()
        cityRepository.save(city)
    }

}