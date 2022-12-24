package com.interpark.tripleworkapi.domain.service.citylist

import com.interpark.tripleworkapi.domain.city.City

interface CityListBySortTypeLoader {
    fun findBySortType(): List<City>
}