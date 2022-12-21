package com.interpark.tripleworkapi.application

import com.interpark.tripleworkapi.domain.CityRepository
import com.interpark.tripleworkapi.domain.Trip
import com.interpark.tripleworkapi.domain.TripRepository
import com.interpark.tripleworkapi.domain.param.TripParam
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import java.util.IllegalFormatException

@Service
class TripApplication(
    private val repository: TripRepository,
    private val cityRepository: CityRepository
) {
    fun create(param: TripParam): Trip {
        // TODO: 존재하지 않는 city면 Exception
        val isExistCities = verifyCities(param.cityIds.map { it.toLong() })
        if (!isExistCities) throw RuntimeException("존재하지 않는 도시입니다.")
        // TODO: 존재하지 않는 user면 Exception
        val trip = Trip(param = param)
        return repository.save(trip)
    }

    fun update(id: Long, param: TripParam): Trip {
        val trip = repository.findById(id).orElseThrow {
            NotFoundException()
        }

        trip.update(param = param)
        return repository.save(trip)
    }

    fun verifyCities(cityIds: List<Long>): Boolean {
        return cityRepository.findAllById(cityIds).isNotEmpty()
    }
}