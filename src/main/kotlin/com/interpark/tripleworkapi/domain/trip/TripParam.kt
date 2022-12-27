package com.interpark.tripleworkapi.domain.trip

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

data class TripParam(
    val title: String,
    val cityId: Long,
    val plan: PlanParam,
    val userId: Long
)

data class PlanParam(
    @DateTimeFormat(style= "yyyy-MM-dd")
    val startedAt: LocalDate,
    @DateTimeFormat(style= "yyyy-MM-dd")
    val endedAt: LocalDate
)
