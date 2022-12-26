package com.interpark.tripleworkapi.controller

import com.interpark.tripleworkapi.application.CityApplication
import com.interpark.tripleworkapi.application.CityListByUserProvider
import com.interpark.tripleworkapi.application.CityProvider
import com.interpark.tripleworkapi.domain.city.City
import com.interpark.tripleworkapi.domain.param.CityParam
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/cities")
class CityController(
    private val provider: CityProvider,
    private val cityListByUserProvider: CityListByUserProvider,
    private val service: CityApplication
) {
    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): City {
        return provider.findById(id)
    }

    @GetMapping
    fun findByUser(@RequestParam userId: Long): List<City> {
        return cityListByUserProvider.findAllByUser(userId = userId)
    }

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