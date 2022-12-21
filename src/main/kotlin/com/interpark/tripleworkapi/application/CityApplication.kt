package com.interpark.tripleworkapi.application

import com.interpark.tripleworkapi.domain.city.City
import com.interpark.tripleworkapi.domain.city.CityRepository
import com.interpark.tripleworkapi.domain.param.CityParam
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service

@Service
class CityApplication(
    private val repository: CityRepository
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
}