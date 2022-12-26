package com.interpark.tripleworkapi.domain.event

import com.interpark.tripleworkapi.domain.city.CityId
import com.interpark.tripleworkapi.domain.user.UserId
import org.springframework.context.ApplicationEvent

class CityViewed(
    val userId: UserId,
    val cityId: CityId
)