package com.interpark.tripleworkapi.domain

import com.interpark.tripleworkapi.domain.trip.Plan
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate

class PlanTest {
    @Test
    fun `Plan을 생성한다 - 시작일이 종료일보다 빠름`() {
        val startedAt = LocalDate.of(2022, 12, 24)
        val endedAt = LocalDate.of(2022, 12, 25)

        assertDoesNotThrow { Plan(startedAt = startedAt, endedAt = endedAt) }
    }

    @Test
    fun `Plan을 생성 - 시작일과 종료일이 같음`() {
        val startedAt = LocalDate.of(2022, 12, 24)
        val endedAt = LocalDate.of(2022, 12, 24)

        assertDoesNotThrow { Plan(startedAt = startedAt, endedAt = endedAt) }
    }

    @Test
    fun `Plan을 생성 - 종료일이 시작일보다 빠름`() {
        val startedAt = LocalDate.of(2022, 12, 24)
        val endedAt = LocalDate.of(2022, 12, 23)

        assertThrows<RuntimeException> { Plan(startedAt = startedAt, endedAt = endedAt) }
    }

}