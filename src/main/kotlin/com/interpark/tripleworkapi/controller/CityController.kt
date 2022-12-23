package com.interpark.tripleworkapi.controller

import com.interpark.tripleworkapi.application.CityApplication
import com.interpark.tripleworkapi.application.CityProvider
import com.interpark.tripleworkapi.domain.city.City
import com.interpark.tripleworkapi.domain.param.CityParam
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/cities")
class CityController(
    private val provider: CityProvider,
    private val service: CityApplication
) {
    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): City {
        return provider.findById(id)
    }

//    @GetMapping
//    fun findByUser() {
//
//    }

    @PostMapping
    fun create(@RequestBody param: CityParam): City {
        return service.create(param)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody param: CityParam): City {
        return service.update(id, param)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        service.delete(id)
    }
}