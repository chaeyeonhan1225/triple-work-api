package com.interpark.tripleworkapi.domain.event

import com.interpark.tripleworkapi.domain.city.CityId
import org.springframework.context.ApplicationEvent

class CityViewed(
    val userId: Long,
    val cityId: CityId
)