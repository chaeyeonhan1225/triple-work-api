package com.interpark.tripleworkapi.application

import com.interpark.tripleworkapi.domain.city.City
import com.interpark.tripleworkapi.domain.city.CityRepository
import com.interpark.tripleworkapi.domain.common.CommonState
import com.interpark.tripleworkapi.domain.city.CityParam
import com.interpark.tripleworkapi.domain.city.GeoPointParam
import com.interpark.tripleworkapi.domain.service.CityDeleteService
import com.interpark.tripleworkapi.domain.service.SequenceGenerator
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CityApplicationTest {
    private val sequenceGenerator = mockk<SequenceGenerator>()
    private val cityRepository = mockk<CityRepository>()
    private val cityDeleteService = mockk<CityDeleteService>()
    private val cityApplication = CityApplication(
        sequenceGenerator = sequenceGenerator,
        repository = cityRepository,
        cityDeleteService = cityDeleteService
    )

    @Test
    fun `city create 성공 테스트`() {
        every {
            sequenceGenerator.generate(City::class.java.simpleName)
        } returns 1

        every {
            cityRepository.save(any())
        } returnsArgument (0)

        val testCityParam = CityParam(
            name = "Seoul",
            countryCode = "KR",
            geoPoint = GeoPointParam(
                37.566536, 126.977966
            )
        )

        val city = cityApplication.create(testCityParam)

        println("id = " + city.id.value)
        assertThat(city.name).isEqualTo(testCityParam.name)
        assertThat(city.countryCode).isEqualTo(testCityParam.countryCode)
        assertThat(city.status).isEqualTo(CommonState.ACTIVE)
    }

}