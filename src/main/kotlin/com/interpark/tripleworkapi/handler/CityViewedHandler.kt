package com.interpark.tripleworkapi.handler

import com.interpark.tripleworkapi.application.CityViewLogApplication
import com.interpark.tripleworkapi.domain.event.CityViewed
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class CityViewedHandler(
    private val cityViewLogApplication: CityViewLogApplication
) {
    @Async
    @EventListener(CityViewed::class)
    fun handleCityViews(cityViewed: CityViewed) {
        println("CityViewed Event received = ${cityViewed.toString()}")
        cityViewLogApplication.create(
            userId = cityViewed.userId,
            cityId = cityViewed.cityId
        )
    }
}