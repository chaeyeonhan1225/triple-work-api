package com.interpark.tripleworkapi.domain.param

import java.util.Date

data class TripParam(
    val title: String,
    val cityIds: List<String>,
    val userId: Long,
    val plan: PlanParam
)

data class PlanParam(
    val startedAt: Date,
    val endedAt: Date
)
