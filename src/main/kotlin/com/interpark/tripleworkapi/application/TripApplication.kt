package com.interpark.tripleworkapi.application

import com.interpark.tripleworkapi.domain.city.CityId
import com.interpark.tripleworkapi.domain.city.CityRepository
import com.interpark.tripleworkapi.domain.exception.NotFoundException
import com.interpark.tripleworkapi.domain.trip.Trip
import com.interpark.tripleworkapi.domain.trip.TripRepository
import com.interpark.tripleworkapi.domain.trip.TripParam
import com.interpark.tripleworkapi.domain.service.SequenceGenerator
import com.interpark.tripleworkapi.domain.trip.TripId
import com.interpark.tripleworkapi.domain.user.UserId
import com.interpark.tripleworkapi.domain.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class TripApplication(
    private val sequenceGenerator: SequenceGenerator,
    private val repository: TripRepository,
    private val cityRepository: CityRepository,
    private val userRepository: UserRepository

) {
    fun create(param: TripParam): Trip {
        val tripId = TripId(sequenceGenerator.generate(Trip::class.java.simpleName))

        verifyCityAndUser(param.cityId, param.userId)

        val trip = Trip(id = tripId, userId = UserId(param.userId), param = param, cityId = CityId(param.cityId))
        return repository.save(trip)
    }

    fun update(id: Long, param: TripParam): Trip {
        verifyCityAndUser(param.cityId, param.userId)

        val tripId = TripId(id)
        val trip = repository.findById(tripId).orElseThrow {
            NotFoundException(message = "존재하지 않는 여행입니다.")
        }

        trip.update(param = param)
        return repository.save(trip)
    }

    private fun verifyCityAndUser(cityId: Long, userId: Long) {
        if (!verifyCity(cityId)) {
            throw NotFoundException(message = "존재하지 않는 도시입니다.")
        }

        if (!verifyUser(userId)) {
            throw NotFoundException(message = "존재하지 않는 유저입니다.")
        }
    }

    private fun verifyCity(cityId: Long) = cityRepository.existsById(CityId(cityId))

    private fun verifyUser(userId: Long) = userRepository.existsById(UserId(userId))

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