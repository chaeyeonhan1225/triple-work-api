package com.interpark.tripleworkapi.domain.trip

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class Plan(
    @Column
    val startedAt: LocalDate,
    @Column
    val endedAt: LocalDate
) {
    init {
        if (endedAt.isBefore(startedAt)) {
            throw RuntimeException("여행 종료 시간은 여행 시작 시작보다 빠를 수 없습니다.")
        }
    }
}