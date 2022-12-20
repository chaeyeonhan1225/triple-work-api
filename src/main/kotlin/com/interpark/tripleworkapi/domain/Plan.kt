package com.interpark.tripleworkapi.domain

import java.util.*
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class Plan (
    @Column
    val startedAt: Date,
    val endedAt: Date
) {

}