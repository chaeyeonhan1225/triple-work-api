package com.interpark.tripleworkapi.domain.trip

import com.fasterxml.jackson.annotation.JsonFormat
import com.interpark.tripleworkapi.domain.exception.InvalidPlanException
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class Plan(
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Column
    val startedAt: LocalDate,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Column
    val endedAt: LocalDate
) {
    init {
        if (endedAt.isBefore(LocalDate.now())) {
            throw InvalidPlanException("여행 종료 시간은 현재보다 미래여야 합니다.")
        }
        else if (endedAt.isBefore(startedAt)) {
            throw InvalidPlanException("여행 종료 시간은 여행 시작 시작보다 빠를 수 없습니다.")
        }
    }
}