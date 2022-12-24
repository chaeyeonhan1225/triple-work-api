package com.interpark.tripleworkapi.application

import com.interpark.tripleworkapi.domain.city.City
import com.interpark.tripleworkapi.domain.city.CityId
import com.interpark.tripleworkapi.domain.city.CityRepository
import com.interpark.tripleworkapi.domain.trip.TripRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CityListByUserProvider(
    private val cityRepository: CityRepository,
    private val tripRepository: TripRepository
) {
    fun findAllByUser(userId: Long): List<City> {
        val sort = CitySort(
            userId = userId,
            orderTypes = listOf(
                CityOrderType.ONGOING_TRIP,
                CityOrderType.IMPENDING_TRIP,
                CityOrderType.RECENTLY_CREATED,
                CityOrderType.RECENTLY_VIEWD,
                CityOrderType.RANDOM
            ),
            limit = 10
        )

        val list = mutableListOf<City>()
        sort.orderTypes.map {

        }
        return listOf()
    }

    fun findCitiesBySortType(type: CityOrderType) =
        when (type) {
            CityOrderType.ONGOING_TRIP -> findOngoingTripCities()
            CityOrderType.IMPENDING_TRIP -> findImpendingTripCities()
            CityOrderType.RECENTLY_CREATED -> findRecentCreatedCities()
            CityOrderType.RECENTLY_VIEWD -> findRecentViewedCities()
            CityOrderType.RANDOM -> findRandomCities()
            else -> listOf()
        }


    fun findOngoingTripCities(): List<City> {
        val trips = tripRepository.findOngoingTrips()
        val cityIds = trips.map { it.cityId }
        return cityRepository.findAllById(cityIds)
    }

    fun findImpendingTripCities(): List<City> {
        val trips = tripRepository.findImpendingTrips(Pageable.ofSize(10))
        val cityIds = trips.map { it.cityId }
        return cityRepository.findAllById(cityIds)
    }

    fun findRecentCreatedCities(): List<City> {
        return listOf()
    }

    fun findRecentViewedCities(): List<City> {
        return listOf()
    }

    fun findRandomCities(): List<City> {
        return listOf()
    }

}

data class CitySort(
    val userId: Long,
    val orderTypes: List<CityOrderType>,
    val limit: Int
)

enum class CityOrderType {
    ONGOING_TRIP,
    IMPENDING_TRIP,
    RECENTLY_CREATED,
    RECENTLY_VIEWD,
    RANDOM
}