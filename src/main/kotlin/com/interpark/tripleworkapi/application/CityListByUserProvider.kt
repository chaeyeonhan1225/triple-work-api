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
            types = listOf(
                CitySortType.ONGOING_TRIP,
                CitySortType.IMPENDING_TRIP,
                CitySortType.RECENTLY_CREATED,
                CitySortType.RECENTLY_VIEWD,
                CitySortType.RANDOM
            ),
            limit = 10
        )

        val list = mutableListOf<City>()
        sort.types.forEach {

        }
        return listOf()
    }

    fun findCitiesBySortType(type: CitySortType) =
        when (type) {
            CitySortType.ONGOING_TRIP -> findOngoingTripCities()
            CitySortType.IMPENDING_TRIP -> findImpendingTripCities()
            CitySortType.RECENTLY_CREATED -> findRecentCreatedCities()
            CitySortType.RECENTLY_VIEWD -> findRecentViewedCities()
            CitySortType.RANDOM -> findRandomCities()
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
    val types: List<CitySortType>,
    val limit: Int
)

enum class CitySortType {
    ONGOING_TRIP,
    IMPENDING_TRIP,
    RECENTLY_CREATED,
    RECENTLY_VIEWD,
    RANDOM
}