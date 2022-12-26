package com.interpark.tripleworkapi.application

import com.interpark.tripleworkapi.domain.city.City
import com.interpark.tripleworkapi.domain.city.CityId
import com.interpark.tripleworkapi.domain.city.CityRepository
import com.interpark.tripleworkapi.domain.exception.NotFoundException
import com.interpark.tripleworkapi.domain.exception.InvalidPlanException
import com.interpark.tripleworkapi.domain.trip.TripRepository
import com.interpark.tripleworkapi.domain.param.CityParam
import com.interpark.tripleworkapi.domain.param.PlanParam
import com.interpark.tripleworkapi.domain.param.TripParam
import com.interpark.tripleworkapi.domain.service.SequenceGenerator
import com.interpark.tripleworkapi.domain.trip.Trip
import com.interpark.tripleworkapi.domain.user.UserId
import com.interpark.tripleworkapi.domain.user.UserRepository
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate

class TripApplicationTest {
    private val sequenceGenerator = mockk<SequenceGenerator>()
    private val tripRepository = mockk<TripRepository>()
    private val cityRepository = mockk<CityRepository>()
    private val userRepository = mockk<UserRepository>()

    private val tripApplication = TripApplication(
        sequenceGenerator = sequenceGenerator,
        repository = tripRepository,
        cityRepository = cityRepository,
        userRepository = userRepository
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
            cityRepository.existsById(CityId(1))
        } returns true

        every {
            userRepository.existsById(UserId(1))
        } returns true


        val testTripParam = TripParam(
            title = "테스트 여행",
            cityId = 1,
            plan = PlanParam(startedAt = LocalDate.now(), endedAt = LocalDate.now()),
            userId = 1
        )

        val newTrip = tripApplication.create(param = testTripParam)
        Assertions.assertThat(newTrip.title).isEqualTo(testTripParam.title)
    }

    @Test
    fun `trip create error 테스트 - 해당하는 도시가 없음`() {
        every {
            sequenceGenerator.generate(Trip::class.java.simpleName)
        } returns 1

        every {
            tripRepository.save(any())
        } returnsArgument (0)

        every {
            cityRepository.existsById(CityId(1))
        } returns false

        every {
            userRepository.existsById(UserId(1))
        } returns true

        val testTripParam = TripParam(
            title = "테스트 여행",
            cityId = 1,
            plan = PlanParam(startedAt = LocalDate.now(), endedAt = LocalDate.now()),
            userId = 1
        )

        assertThrows<NotFoundException> { tripApplication.create(param = testTripParam) }
    }

    @Test
    fun `trip create error 테스트 - 유저가 없음`() {
        every {
            sequenceGenerator.generate(Trip::class.java.simpleName)
        } returns 1

        every {
            tripRepository.save(any())
        } returnsArgument (0)

        every {
            cityRepository.existsById(CityId(1))
        } returns true

        every {
            userRepository.existsById(UserId(1))
        } returns false

        val testTripParam = TripParam(
            title = "테스트 여행",
            cityId = 1,
            plan = PlanParam(startedAt = LocalDate.now(), endedAt = LocalDate.now()),
            userId = 1
        )

        assertThrows<NotFoundException> { tripApplication.create(param = testTripParam) }
    }

    @Test
    fun `trip create error 테스트 - invalid plan`() {
        every {
            sequenceGenerator.generate(Trip::class.java.simpleName)
        } returns 1

        every {
            tripRepository.save(any())
        } returnsArgument (0)

        every {
            cityRepository.existsById(CityId(1))
        } returns true

        every {
            userRepository.existsById(UserId(1))
        } returns true

        val testTripParam = TripParam(
            title = "테스트 여행",
            cityId = 1,
            plan = PlanParam(startedAt = LocalDate.now(), endedAt = LocalDate.now().minusDays(3)),
            userId = 1
        )

        assertThrows<InvalidPlanException> { tripApplication.create(param = testTripParam) }
    }
}