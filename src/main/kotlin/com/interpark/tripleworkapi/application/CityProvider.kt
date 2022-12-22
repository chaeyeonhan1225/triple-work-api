package com.interpark.tripleworkapi.application

import com.interpark.tripleworkapi.domain.city.City
import com.interpark.tripleworkapi.domain.city.CityId
import com.interpark.tripleworkapi.domain.city.CityRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CityProvider(
    private val repository: CityRepository
) {
    // TODO: 조회 이벤트 추가
    fun findById(id: Long): City {
        val cityId = CityId(id)
        return repository.findById(cityId).orElseThrow {
            NotFoundException()
        }
    }
}