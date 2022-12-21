package com.interpark.tripleworkapi.domain

import com.interpark.tripleworkapi.domain.city.City
import com.interpark.tripleworkapi.domain.param.CityParam
import org.junit.jupiter.api.Test

class CityTest {
    @Test
    fun test() {
        val param = CityParam(
            name = "Seoul",
            countryCode = "KR"
        )
        val city = City(param = param)
    }
}