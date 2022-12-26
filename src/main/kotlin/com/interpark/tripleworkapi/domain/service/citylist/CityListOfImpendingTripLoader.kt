package com.interpark.tripleworkapi.domain.service.citylist

import com.interpark.tripleworkapi.domain.city.City
import com.interpark.tripleworkapi.domain.city.CityId
import com.interpark.tripleworkapi.domain.city.CityRepository
import com.interpark.tripleworkapi.domain.trip.TripRepository
import com.interpark.tripleworkapi.domain.user.UserId
import org.springframework.core.annotation.Order
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
@Order(2)
class CityListOfImpendingTripLoader(
    private val tripRepository: TripRepository,
    private val cityRepository: CityRepository
) : CityListLoader {
    override fun loadCityList(size: Int, excludedCityIds: List<CityId>, userId: UserId?): List<City> {
        println(this::class.java.simpleName + excludedCityIds.map { it.value })
        userId?.let {
            val cityIds = tripRepository.findImpendingTrips(
                userId = userId,
                excludedCityIds = excludedCityIds,
                at = LocalDate.now().minusWeeks(1),
                pageable = Pageable.ofSize(size)
            ).map { it.cityId }
            return cityRepository.findAllById(cityIds)
        }

        return listOf()
    }
}