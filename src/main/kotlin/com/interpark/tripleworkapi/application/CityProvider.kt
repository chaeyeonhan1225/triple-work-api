package com.interpark.tripleworkapi.application

import com.interpark.tripleworkapi.domain.city.City
import com.interpark.tripleworkapi.domain.city.CityId
import com.interpark.tripleworkapi.domain.city.CityRepository
import com.interpark.tripleworkapi.domain.city.CityViewDao
import com.interpark.tripleworkapi.domain.event.CityViewed
import com.interpark.tripleworkapi.domain.event.EventPublisher
import com.interpark.tripleworkapi.domain.exception.NotFoundException
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CityProvider(
    private val repository: CityRepository,
    private val eventPublisher: EventPublisher,
    // private val cityViewDao: CityViewDao
) {
    // TODO: 조회 이벤트 추가
    fun findById(id: Long): City {
        val cityId = CityId(id)

        val city = repository.findById(cityId).orElseThrow {
            NotFoundException(message = "존재하지 않는 도시입니다.")
        }

        eventPublisher.publish(CityViewed(userId = 0, cityId = cityId))
        return city
    }

//    fun findAllByUser(): List<City> {
//        return cityViewDao.findAllCitiesByUser()
//    }
}