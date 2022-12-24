package com.interpark.tripleworkapi.domain.trip

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class Plan (
    @Column
    val startedAt: LocalDate,
    @Column
    val endedAt: LocalDate
) {

}