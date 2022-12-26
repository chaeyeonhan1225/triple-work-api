package com.interpark.tripleworkapi.domain.param

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.interpark.tripleworkapi.domain.user.UserId
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date

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
