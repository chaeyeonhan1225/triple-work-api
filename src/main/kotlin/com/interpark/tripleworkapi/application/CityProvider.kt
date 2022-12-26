package com.interpark.tripleworkapi.application

import com.interpark.tripleworkapi.domain.city.City
import com.interpark.tripleworkapi.domain.city.CityId
import com.interpark.tripleworkapi.domain.city.CityRepository
import com.interpark.tripleworkapi.domain.event.CityViewed
import com.interpark.tripleworkapi.domain.event.EventPublisher
import com.interpark.tripleworkapi.domain.exception.NotFoundException
import com.interpark.tripleworkapi.domain.user.UserId
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CityProvider(
    private val repository: CityRepository,
    private val eventPublisher: EventPublisher,
) {
    fun findById(id: Long, userId: Long?): City {
        val cityId = CityId(id)
        val viewerId = UserId(userId ?: 0)
        val city = repository.findById(cityId).orElseThrow {
            NotFoundException(message = "존재하지 않는 도시입니다.")
        }

        eventPublisher.publish(
            CityViewed(
                userId = viewerId, cityId = cityId
            )
        )
        return city
    }
}