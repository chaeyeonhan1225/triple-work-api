package com.interpark.tripleworkapi.domain.service.citylist

import com.interpark.tripleworkapi.domain.city.City
import com.interpark.tripleworkapi.domain.city.CityId
import com.interpark.tripleworkapi.domain.city.CityRepository
import com.interpark.tripleworkapi.domain.user.UserId
import com.interpark.tripleworkapi.domain.viewlog.CityViewLogRepository
import org.springframework.core.annotation.Order
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
@Order(4)
class CityListOfRecentlyViewedLoader(
    private val cityRepository: CityRepository,
    private val cityViewLogRepository: CityViewLogRepository
) : CityListLoader {
    override fun loadCityList(size: Int, excludedCityIds: List<CityId>, userId: UserId?): List<City> {
        println(this::class.java.simpleName + excludedCityIds.map { it.value })
//        val cityIds = cityViewLogRepository.findCityViewLogRecentlyCreatedByUserId(
//            userId = userId,
//            excludedCityIds = excludedCityIds,
//            at = LocalDateTime.now().minusWeeks(1),
//            pageable = Pageable.ofSize(size)
//        ).map { it.cityId }

//        println("cityIds = " + cityIds.map { it.value })

        userId?.let {
            return cityRepository.findCitiesOfRecentlyCreatedViewLog( userId = userId,
                excludedCityIds = excludedCityIds,
                at = LocalDateTime.now().minusWeeks(1),
                pageable = Pageable.ofSize(size))
        }

        return listOf()
    }
}