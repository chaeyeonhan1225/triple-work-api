package com.interpark.tripleworkapi.domain.param

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date

data class TripParam(
    val title: String,
    val cityId: Long,
    val plan: PlanParam
)

data class PlanParam(
    @DateTimeFormat(style= "yyyy-MM-dd")
    // @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    val startedAt: LocalDate,
    @DateTimeFormat(style= "yyyy-MM-dd")
    // @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    val endedAt: LocalDate
)
