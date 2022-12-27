package com.interpark.tripleworkapi.domain.city

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class GeoPoint(
    @Column
    val latitude: Double,   // 위도
    @Column
    val longitude: Double   // 경도
)