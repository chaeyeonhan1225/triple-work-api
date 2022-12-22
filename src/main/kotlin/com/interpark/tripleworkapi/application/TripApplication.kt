package com.interpark.tripleworkapi.application

import com.interpark.tripleworkapi.domain.city.CityRepository
import com.interpark.tripleworkapi.domain.trip.Trip
import com.interpark.tripleworkapi.domain.trip.TripRepository
import com.interpark.tripleworkapi.domain.param.TripParam
import com.interpark.tripleworkapi.domain.trip.TripId
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TripApplication(
    private val repository: TripRepository,
    private val cityRepository: CityRepository
) {
    fun create(param: TripParam): Trip {
        // TODO: 존재하지 않는 city면 Exception
        verifyCities(param.cityIds.map { it.toLong() })

        val tripId = TripId(1)
        // TODO: 존재하지 않는 user면 Exception
        val trip = Trip(id = tripId, param = param)
        return repository.save(trip)
    }

    fun update(id: Long, param: TripParam): Trip {
        verifyCities(param.cityIds.map { it.toLong() })

        val trip = repository.findById(id).orElseThrow {
            NotFoundException()
        }

        trip.update(param = param)
        return repository.save(trip)
    }

    fun verifyCities(cityIds: List<Long>) {
        val isExistCities = cityRepository.findAllById(cityIds).isNotEmpty()
        if (!isExistCities) {
            throw RuntimeException("존재하지 않는 도시입니다.")
        }
    }

    fun delete(id: Long): Boolean {
        val trip = repository.findById(id).orElseThrow {
            NotFoundException()
        }
        trip.delete()
        repository.save(trip)
        return true
    }

}