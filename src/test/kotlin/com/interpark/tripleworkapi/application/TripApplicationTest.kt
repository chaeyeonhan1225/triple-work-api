package com.interpark.tripleworkapi.application

import com.interpark.tripleworkapi.domain.city.City
import com.interpark.tripleworkapi.domain.city.CityId
import com.interpark.tripleworkapi.domain.city.CityRepository
import com.interpark.tripleworkapi.domain.trip.TripRepository
import com.interpark.tripleworkapi.domain.param.CityParam
import com.interpark.tripleworkapi.domain.param.PlanParam
import com.interpark.tripleworkapi.domain.param.TripParam
import com.interpark.tripleworkapi.domain.service.SequenceGenerator
import com.interpark.tripleworkapi.domain.trip.Trip
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import java.util.Date

class TripApplicationTest {
    private val sequenceGenerator = mockk<SequenceGenerator>()
    private val tripRepository = mockk<TripRepository>()
    private val cityRepository = mockk<CityRepository>()

    private val tripApplication = TripApplication(
        sequenceGenerator = sequenceGenerator,
        repository = tripRepository,
        cityRepository = cityRepository
    )


    @Test
    fun `trip create 성공 테스트`() {
        val mockCity = City(
            id = CityId(1),
            param = CityParam(name = "Seoul", countryCode = "KR")
        )

        every {
            sequenceGenerator.generate(Trip::class.java.simpleName)
        } returns 1

        every {
            tripRepository.save(any())
        } returnsArgument (0)

        every {
            cityRepository.findAllById(listOf(CityId(1)))
        } returns listOf(mockCity)

        val testTripParam = TripParam(
            title = "테스트 여행",
            cityId = 1,
            plan = PlanParam(startedAt = Date(), endedAt = Date())
        )

        val newTrip = tripApplication.create(param = testTripParam)
        Assertions.assertThat(newTrip.title).isEqualTo(testTripParam.title)
    }

    @Test
    fun `trip create error 테스트 - 해당하는 도시가 없음`() {
        every {
            tripRepository.save(any())
        } returnsArgument (0)

        every {
            cityRepository.findAllById(listOf(CityId(1)))
        } returns listOf()

        val testTripParam = TripParam(
            title = "테스트 여행",
            cityId = 1,
            plan = PlanParam(startedAt = Date(), endedAt = Date())
        )

        assertThrows<RuntimeException> { tripApplication.create(param = testTripParam) }
    }
}