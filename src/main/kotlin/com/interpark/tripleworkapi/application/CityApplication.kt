package com.interpark.tripleworkapi.application

import com.interpark.tripleworkapi.domain.city.City
import com.interpark.tripleworkapi.domain.city.CityId
import com.interpark.tripleworkapi.domain.city.CityRepository
import com.interpark.tripleworkapi.domain.exception.CityUnableDeleteException
import com.interpark.tripleworkapi.domain.exception.NotFoundException
import com.interpark.tripleworkapi.domain.param.CityParam
import com.interpark.tripleworkapi.domain.service.CityDeleteService
import com.interpark.tripleworkapi.domain.service.SequenceGenerator
import com.interpark.tripleworkapi.domain.trip.TripRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CityApplication(
    private val sequenceGenerator: SequenceGenerator,
    private val repository: CityRepository,
    private val cityDeleteService: CityDeleteService
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
        val cityId = CityId(id)
        cityDeleteService.delete(cityId)
        return true
    }
}