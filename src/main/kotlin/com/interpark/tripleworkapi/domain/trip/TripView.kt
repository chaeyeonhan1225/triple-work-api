package com.interpark.tripleworkapi.domain.trip

import com.interpark.tripleworkapi.domain.city.City

data class TripView(
    val cities: List<City>,
    private val trip: Trip,
) {
    val id = trip.id
    val userId = trip.userId
    val title = trip.title
    val plan = trip.plan
    val status = trip.status
}
