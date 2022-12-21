package com.interpark.tripleworkapi.controller

import com.interpark.tripleworkapi.application.TripApplication
import com.interpark.tripleworkapi.domain.trip.Trip
import com.interpark.tripleworkapi.domain.param.TripParam
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/trips")
class TripController(
    private val service: TripApplication
) {
    @GetMapping
    fun findById() {

    }

    @PostMapping
    fun create(@RequestBody param: TripParam): Trip {
        return service.create(param)
    }

    @PutMapping
    fun update() {

    }

    @DeleteMapping
    fun delete() {

    }
}