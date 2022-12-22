package com.interpark.tripleworkapi.domain.trip

import com.interpark.tripleworkapi.domain.common.CommonState
import org.springframework.data.jpa.domain.Specification

class TripSpecification(
    private val filter: TripFilter
) {
     fun byCityId(cityId: Long) = Specification<Trip> { root, query, criteriaBuilder ->
         root.join<Trip, TripCity>("citiesToTrip").get<TripCity>("cityId").`in`(cityId)
     }
    fun build(): Specification<Trip> {
        var spec = Specification<Trip> { root, query, criteriaBuilder ->
            criteriaBuilder.notEqual(
                root.get<CommonState>("status"),
                CommonState.DELETED
            )
        }
        filter?.run {
            this.cityId?.let {
                spec = spec.and(byCityId(cityId = it))
            }
        }
        return spec
    }
}

data class TripFilter (
    val cityId: Long
)



