package com.interpark.tripleworkapi.application

import com.interpark.tripleworkapi.domain.TripRepository
import com.interpark.tripleworkapi.domain.param.PlanParam
import com.interpark.tripleworkapi.domain.param.TripParam
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.util.Date

class TripApplicationTest {
    private val tripRepository = mockk<TripRepository>()
    private val tripApplication = TripApplication(repository = tripRepository)

    @Test
    fun `trip create 성공 테스트` () {
        every {
            tripRepository.save(any())
        } returnsArgument (0)

        val testTripParam = TripParam(
            title = "테스트 여행",
            cityIds = listOf("1"),
            plan = PlanParam(startedAt = Date(), endedAt = Date())
        )

        val newTrip = tripApplication.create(param = testTripParam)
        Assertions.assertThat(newTrip.title).isEqualTo(testTripParam.title)
    }
}