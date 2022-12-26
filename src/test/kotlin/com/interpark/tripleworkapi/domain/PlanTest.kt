package com.interpark.tripleworkapi.domain

import com.interpark.tripleworkapi.domain.exception.InvalidPlanException
import com.interpark.tripleworkapi.domain.trip.Plan
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate

class PlanTest {
    @Test
    fun `Plan을 생성한다 - 시작일이 종료일보다 빠름`() {
        val startedAt = LocalDate.now()
        val endedAt = LocalDate.now().plusYears(1)

        assertDoesNotThrow { Plan(startedAt = startedAt, endedAt = endedAt) }
    }

    @Test
    fun `Plan을 생성 - 시작일과 종료일이 같음`() {
        val startedAt = LocalDate.now()
        val endedAt = LocalDate.now().plusYears(1)

        assertDoesNotThrow { Plan(startedAt = startedAt, endedAt = endedAt) }
    }

    @Test
    fun `Plan을 생성 - 종료일이 시작일보다 빠름`() {
        val startedAt = LocalDate.now().plusYears(2)
        val endedAt = LocalDate.now().plusYears(1)

        assertThrows<InvalidPlanException> { Plan(startedAt = startedAt, endedAt = endedAt) }
    }

    @Test
    fun `Plan을 생성 - 종료일이 현재보다 과거임`() {
        val startedAt = LocalDate.now().minusYears(2)
        val endedAt = LocalDate.now().minusYears(1)

        assertThrows<InvalidPlanException> { Plan(startedAt = startedAt, endedAt = endedAt) }
    }

}