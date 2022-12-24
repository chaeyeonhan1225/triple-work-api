package com.interpark.tripleworkapi.application

import com.interpark.tripleworkapi.domain.city.City
import com.interpark.tripleworkapi.domain.city.CityId
import com.interpark.tripleworkapi.domain.city.CityRepository
import com.interpark.tripleworkapi.domain.exception.CityUnableDeleteException
import com.interpark.tripleworkapi.domain.exception.NotFoundException
import com.interpark.tripleworkapi.domain.param.CityParam
import com.interpark.tripleworkapi.domain.service.SequenceGenerator
import com.interpark.tripleworkapi.domain.trip.TripRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CityApplication(
    private val sequenceGenerator: SequenceGenerator,
    private val repository: CityRepository,
    private val tripRepository: TripRepository
) {
    fun create(param: CityParam): City {
        val cityId = CityId(sequenceGenerator.generate(City::class.java.simpleName))

        val city = City(id = cityId, param = param)
        return repository.save(city)
    }

    fun update(id: Long, param: CityParam): City {
        val cityId = CityId(id)

        val city = repository.findById(cityId).orElseThrow {
            NotFoundException(message = "존재하지 않는 도시입니다.")
        }

        city.update(param)
        return repository.save(city)
    }

    fun delete(id: Long): Boolean {
        // TODO: 도메인 서비스로 분리
        val cityId = CityId(id)
        val trips = tripRepository.findAllByCityId(cityId)
        if (trips.isNotEmpty()) {
            throw CityUnableDeleteException(message = "해당 도시는 삭제할 수 없습니다.")
        }

        val city = repository.findById(cityId).orElseThrow {
            NotFoundException(message = "존재하지 않는 도시입니다.")
        }
        city.delete()
        repository.save(city)
        return true
    }
}