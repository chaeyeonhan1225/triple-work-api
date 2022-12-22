package com.interpark.tripleworkapi.application

import com.interpark.tripleworkapi.domain.city.City
import com.interpark.tripleworkapi.domain.city.CityRepository
import com.interpark.tripleworkapi.domain.param.CityParam
import com.interpark.tripleworkapi.domain.trip.TripFilter
import com.interpark.tripleworkapi.domain.trip.TripRepository
import com.interpark.tripleworkapi.domain.trip.TripSpecification
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Service
class CityApplication(
    private val repository: CityRepository,
    private val tripRepository: TripRepository
) {
    fun create(param: CityParam): City {
        val city = City(param = param)
        return repository.save(city)
    }

    fun update(id: String, param: CityParam): City {
        val city = repository.findById(id.toLong()).orElseThrow {
            NotFoundException()
        }

        city.update(param)
        return repository.save(city)
    }

    fun delete(id: Long): Boolean {
        // 1. 도시를 포함하는 여행이 있는지 검색
        val tripSpecification = TripSpecification(filter = TripFilter(cityId =  1)).build()

        val trips = tripRepository.findAll(tripSpecification)
        println("trips = $trips")
        return true
    }
}