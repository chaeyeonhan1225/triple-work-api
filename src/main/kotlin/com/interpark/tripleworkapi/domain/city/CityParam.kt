package com.interpark.tripleworkapi.domain.city

data class CityParam(
    val name: String,
    val countryCode: String?,
    val geoPoint: GeoPointParam
)

data class GeoPointParam(
    val latitude: Double,
    val longitude: Double
)