package com.interpark.tripleworkapi.application

import com.interpark.tripleworkapi.domain.city.City
import com.interpark.tripleworkapi.domain.city.CityId
import com.interpark.tripleworkapi.domain.city.CityRepository
import com.interpark.tripleworkapi.domain.service.citylist.CityListLoader
import com.interpark.tripleworkapi.domain.trip.TripRepository
import com.interpark.tripleworkapi.domain.user.UserId
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.math.max
import kotlin.run as loop

@Service
@Transactional(readOnly = true)
class CityListByUserProvider(
    private val loaders: List<CityListLoader>
) {
    fun findAllByUser(userId: Long?): List<City> {
        val maxSize = 10
        val list = mutableListOf<City>()

        loop load@{
            loaders.forEach {
                val excludedCityIds = list.map {
                    it.id
                }
                val result = it.loadCityList(maxSize, excludedCityIds, userId?.let { UserId(userId) })

                list.addAll(
                    result
                )
                if (list.size >= 10) {
                    return@load
                }
            }
        }

        return when {
            list.size > 10 -> list.slice(0..9)
            else -> list
        }
    }
}