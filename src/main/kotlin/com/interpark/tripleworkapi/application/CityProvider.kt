package com.interpark.tripleworkapi.application

import com.interpark.tripleworkapi.domain.City
import com.interpark.tripleworkapi.domain.CityRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CityProvider(
    private val repository: CityRepository
) {
    fun findById(id: String): City {
        val cityId = id.toLong()
        return repository.findById(cityId).orElseThrow {
            NotFoundException()
        }
    }
}