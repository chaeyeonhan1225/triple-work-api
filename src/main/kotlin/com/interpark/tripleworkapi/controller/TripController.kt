package com.interpark.tripleworkapi.controller

import com.interpark.tripleworkapi.application.TripApplication
import com.interpark.tripleworkapi.application.TripProvider
import com.interpark.tripleworkapi.domain.trip.Trip
import com.interpark.tripleworkapi.domain.param.TripParam
import com.interpark.tripleworkapi.domain.trip.TripView
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/trips")
class TripController(
    private val service: TripApplication,
    private val provider: TripProvider
) {
    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): TripView {
        return provider.findById(id)
    }

    @PostMapping
    fun create(@RequestBody param: TripParam): Trip {
        return service.create(param)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: String, @RequestBody param: TripParam): Trip {
        return service.update(id = id.toLong(), param = param)
    }

    @DeleteMapping
    fun delete() {

    }
}