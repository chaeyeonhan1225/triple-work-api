package com.interpark.tripleworkapi.domain.city

import com.interpark.tripleworkapi.domain.trip.QTrip
import com.interpark.tripleworkapi.domain.viewlog.QCityViewLog
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class CityViewDao(
    private val jpaQueryFactory: JPAQueryFactory
) {
    fun findAllCitiesByUser(): List<City> {
        return jpaQueryFactory.selectFrom(
            QCity.city
        ).join(QCityViewLog.cityViewLog).fetch()
        // return listOf()
    }
//    fun findCitiesInTrip(): List<City> {
//        val trips = jpaQueryFactory.selectFrom()
//    }
}