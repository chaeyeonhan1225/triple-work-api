package com.interpark.tripleworkapi.application

import com.interpark.tripleworkapi.domain.CityRepository
import com.interpark.tripleworkapi.domain.CommonState
import com.interpark.tripleworkapi.domain.param.CityParam
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CityApplicationTest {
    private val cityRepository = mockk<CityRepository>()
    private val cityApplication = CityApplication(repository = cityRepository)

    @Test
    fun `city create 성공 테스트`() {
        every {
            cityRepository.save(any())
        } returnsArgument (0)

        val testCityParam = CityParam(
            name = "Seoul",
            countryCode = "KR"
        )

        val city = cityApplication.create(testCityParam)

        println("id = " + city.id)
        assertThat(city.name).isEqualTo(testCityParam.name)
        assertThat(city.countryCode).isEqualTo(testCityParam.countryCode)
        assertThat(city.status).isEqualTo(CommonState.ACTIVE)
    }

}