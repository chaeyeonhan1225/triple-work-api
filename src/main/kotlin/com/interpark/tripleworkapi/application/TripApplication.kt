package com.interpark.tripleworkapi.application

import com.interpark.tripleworkapi.domain.city.CityId
import com.interpark.tripleworkapi.domain.city.CityRepository
import com.interpark.tripleworkapi.domain.exception.NotFoundException
import com.interpark.tripleworkapi.domain.trip.Trip
import com.interpark.tripleworkapi.domain.trip.TripRepository
import com.interpark.tripleworkapi.domain.param.TripParam
import com.interpark.tripleworkapi.domain.service.SequenceGenerator
import com.interpark.tripleworkapi.domain.trip.TripId
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TripApplication(
    private val sequenceGenerator: SequenceGenerator,
    private val repository: TripRepository,
    private val cityRepository: CityRepository,

) {
    fun create(param: TripParam): Trip {
        val tripId = TripId(sequenceGenerator.generate(Trip::class.java.simpleName))

        if (!verifyCities(param.cityId)) {
            throw NotFoundException(message = "존재하지 않는 여행입니다.")
        }

        // TODO: 존재하지 않는 user면 Exception
        val trip = Trip(id = tripId, param = param, cityId = CityId(param.cityId))
        return repository.save(trip)
    }

    fun update(id: Long, param: TripParam): Trip {
        if (!verifyCities(param.cityId)) {
            throw NotFoundException(message = "존재하지 않는 여행입니다.")
        }
        val tripId = TripId(id)
        val trip = repository.findById(tripId).orElseThrow {
            NotFoundException(message = "존재하지 않는 여행입니다.")
        }

        trip.update(param = param)
        return repository.save(trip)
    }

    fun verifyCities(cityId: Long) = cityRepository.existsById(CityId(cityId))

    fun delete(id: Long): Boolean {
        val tripId = TripId(id)
        val trip = repository.findById(tripId).orElseThrow {
            NotFoundException(message = "존재하지 않는 여행입니다.")
        }
        trip.delete()
        repository.save(trip)
        return true
    }

}