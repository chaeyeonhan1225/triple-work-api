package com.interpark.tripleworkapi.domain.service.citylist

import com.interpark.tripleworkapi.domain.city.City
import com.interpark.tripleworkapi.domain.city.CityId
import com.interpark.tripleworkapi.domain.user.UserId

interface CityListLoader {
    fun loadCityList(size: Int, excludedCityIds: List<CityId>, userId: UserId): List<City>
}